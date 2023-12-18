package com.bangkit.genaidclean.ui.screen.user.cekbansos

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusListItem
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.launch

class CekBansosViewModel(private val repo: AppRepository): ViewModel() {

    private val _userAdress = mutableStateOf<UserProfileResponse?>(null)
    val userAdress: State<UserProfileResponse?> = _userAdress

    private val _userStatusName = mutableStateOf<StatusBansosResponse?>(null)
    val userStatusName: State<StatusBansosResponse?> = _userStatusName

    private val _statusBansos = mutableStateOf<List<StatusListItem>>(emptyList())
    val statusListBansos: State<List<StatusListItem>> get() = _statusBansos


    fun getUserAdress() {
        viewModelScope.launch {
            _userAdress.value = repo.getUserProfile()
        }
    }

    fun getStatusName() {
        viewModelScope.launch {
            _userStatusName.value = repo.getStatusName()
        }
    }

    fun fetchStatusList() {
        viewModelScope.launch {
            try {
                _statusBansos.value = repo.getStatusBansos()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}

