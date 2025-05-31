package com.example.matchmatee.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmatee.data.local.Database
import com.example.matchmatee.data.local.DatabaseProvider
import com.example.matchmatee.data.remote.RetrofitInstance
import com.example.matchmatee.data.repository.UserProfileRepository
import com.example.matchmatee.domain.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(context: Context) : ViewModel() {
    private val db = DatabaseProvider.getDatabase(context)
    private val repo = UserProfileRepository(
        context,
        dao = db.userProfileDao(),
        api = RetrofitInstance.api
    )
    val profiles: StateFlow<List<UserProfile>> = repo.getUndecidedProfiles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        observeProfiles()
        //insertDummyProfiles()
    }

    private fun observeProfiles() {
        viewModelScope.launch {
            val response = RetrofitInstance.api.getUsers()
            Log.d("plk", response.results.toString())
            repo.syncProfiles()
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