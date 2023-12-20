package com.bangkit.genaidclean

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.data.repository.AppRepository
import kotlinx.coroutines.launch

class AdminActivityViewModel(private val repo: AppRepository): ViewModel() {


    fun getSession(): LiveData<UserModel> {
        return repo.getSession().asLiveData()
    }

    fun logOut(){
        viewModelScope.launch {
            repo.clearSession()
        }
    }

}