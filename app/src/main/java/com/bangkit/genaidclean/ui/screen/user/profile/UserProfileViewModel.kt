package com.bangkit.genaidclean.ui.screen.user.profile

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.launch

class UserProfileViewModel (private val repo: AppRepository): ViewModel() {


    fun logout() {
        viewModelScope.launch {
            repo.clearSession()
        }
    }

    private val _userProfile = mutableStateOf<UserProfileResponse?>(null)
    val userProfile: State<UserProfileResponse?> = _userProfile

    fun getUserProfile() {
        viewModelScope.launch {
            _userProfile.value = repo.getUserProfile()
        }
    }
}