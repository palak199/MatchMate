package com.example.matchmatee.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmatee.data.repository.UserProfileRepository
import com.example.matchmatee.domain.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeViewModel(context: Context) : ViewModel() {
    private val repo = UserProfileRepository(context)

    private val _profiles = MutableStateFlow<List<UserProfile>>(emptyList())
    val profiles: StateFlow<List<UserProfile>> = _profiles.asStateFlow()

    init {
        observeProfiles()
        //insertDummyProfiles()
    }

    private fun observeProfiles() {
        viewModelScope.launch {
            repo.getAllProfiles().collect() {
                list -> _profiles.value = list
            }
        }
    }

    fun acceptProfile(profile: UserProfile) {
        Log.d("plk", "called accept profile for" + profile.name)
        viewModelScope.launch {
            repo.updateProfile(profile.copy(isAccepted = true))
        }
    }

    fun rejectProfile(profile: UserProfile) {
        viewModelScope.launch {
            repo.updateProfile(profile.copy(isAccepted = false))
        }
    }

}