package com.bangkit.genaidclean

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.data.repository.AppRepository

class MainViewModel(private val repo: AppRepository) : ViewModel() {

    fun getSession(): LiveData<UserModel>{
        return repo.getSession().asLiveData()
    }

}