package com.bangkit.genaidclean.ui.screen.user.profile

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.user.UpdateProfileResponse
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import com.bangkit.genaidclean.utils.State as DataState

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


    private val _updateEmailState =
        MutableStateFlow<DataState<UpdateProfileResponse>>(DataState.Loading)
    val updateEmailState: StateFlow<DataState<UpdateProfileResponse>> = _updateEmailState
    fun updateEmail(email:String){
        viewModelScope.launch {
            try {
                repo.updateEmail(email)
                    .catch{
                        _updateEmailState.value = DataState.Error(it.message)
                    }
                    .collect{
                        _updateEmailState.value = DataState.Success(it)
                    }
            } catch (e: Exception) {
                _updateEmailState.value = DataState.Error(e.message)
            }

        }
    }

    private val _updatePhone =
        MutableStateFlow<DataState<UpdateProfileResponse>>(DataState.Loading)
    val updatePhone: StateFlow<DataState<UpdateProfileResponse>> = _updatePhone
    fun updatePhone(phone:String){
        viewModelScope.launch {
            try {
                repo.updatePhone(phone)
                    .catch{
                        _updatePhone.value = DataState.Error(it.message)
                    }
                    .collect{
                        _updatePhone.value = DataState.Success(it)
                    }
            } catch (e: Exception) {
                _updatePhone.value = DataState.Error(e.message)
            }

        }
    }
}