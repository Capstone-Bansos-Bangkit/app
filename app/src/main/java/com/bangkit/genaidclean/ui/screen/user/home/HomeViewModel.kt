package com.bangkit.genaidclean.ui.screen.user.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: AppRepository) : ViewModel() {

    private val _userProfile = mutableStateOf<UserProfileResponse?>(null)
    val userProfile: State<UserProfileResponse?> = _userProfile


    //get user profile
    fun getUserProfile(){
        viewModelScope.launch {
            _userProfile.value = repository.getUserProfile()
        }
    }

    //get bansos lists
    private val _groupedBansos = MutableStateFlow<Map<Char, List<ResultItem>>>(emptyMap())
    val groupedBansos: MutableStateFlow<Map<Char, List<ResultItem>>> get() = _groupedBansos

    private fun loadGroupedBansos() {
        viewModelScope.launch {
            try {
                val allBansos = repository.getAllProfBansos() // Memanggil di dalam coroutine
                val groupedBansos = allBansos
                    .sortedBy { it.name}
                    .groupBy { it.name[0] }
                _groupedBansos.value = groupedBansos
            } catch (e: Exception) {
                // Handle error, if any
                e.printStackTrace()
            }
        }
    }

    init {
        loadGroupedBansos()
    }
}