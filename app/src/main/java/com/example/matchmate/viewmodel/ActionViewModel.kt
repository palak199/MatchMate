package com.example.matchmate.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.matchmate.data.local.DatabaseProvider
import com.example.matchmate.data.remote.RetrofitInstance
import com.example.matchmate.data.repository.UserProfileRepository
import com.example.matchmate.domain.UserProfile
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ActionViewModel(context: Context): ViewModel() {
    private val db = DatabaseProvider.getDatabase(context)
    private val repo = UserProfileRepository(
        context,
        dao = db.userProfileDao(),
        api = RetrofitInstance.api
    )
    private val _profiles = MutableStateFlow<List<UserProfile>>(emptyList())
    val profiles: StateFlow<List<UserProfile>> = _profiles.asStateFlow()

    init {
        observeAcceptedProfiles()
    }

    private fun observeAcceptedProfiles() {
        viewModelScope.launch {
            repo.getAcceptedProfiles().collect() {
                    list -> _profiles.value = list
            }
        }
    }

}