package com.example.matchmate.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.matchmate.data.local.DatabaseProvider
import com.example.matchmate.data.remote.RetrofitInstance
import com.example.matchmate.data.repository.UserProfileRepository
import com.example.matchmate.domain.UserProfile
import com.example.matchmate.utils.InternetCheck
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(context: Context) : ViewModel() {
    private val db = DatabaseProvider.getDatabase(context)
    private val repo = UserProfileRepository(
        context,
        dao = db.userProfileDao(),
        api = RetrofitInstance.api
    )
    private val _msg = MutableSharedFlow<String>()
    val msg = _msg.asSharedFlow()
    val profiles: StateFlow<List<UserProfile>> = repo.getUndecidedProfiles()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        viewModelScope.launch {
            observeProfiles(context)
        }
    }

    private fun observeProfiles(context: Context) {
        refreshProfiles(context)
    }

    fun refreshProfiles(context: Context) {
        viewModelScope.launch {
            if (!InternetCheck.isInternetAvailable(context)) {
                Log.e("plk", "No Internet")
                _msg.emit("No internet connection")
                return@launch
            }
            Log.d("plk", "internet h")
            val result = repo.syncProfiles()
            if(!result) _msg.emit("Failed to get new profiles")
        }
    }

    fun acceptProfile(profile: UserProfile) {
        viewModelScope.launch {
            repo.updateProfile(profile.copy(isAccepted = true))
        }
    }

    fun rejectProfile(profile: UserProfile) {
        viewModelScope.launch {
            repo.updateProfile(profile.copy(isAccepted = false))
        }
    }

    companion object {
        fun provideFactory(context: Context): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return HomeViewModel(context.applicationContext) as T
                }
            }
    }

}