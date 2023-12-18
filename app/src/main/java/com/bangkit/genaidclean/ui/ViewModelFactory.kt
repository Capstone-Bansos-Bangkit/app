package com.bangkit.genaidclean.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.ui.screen.user.cekbansos.CekBansosViewModel
import com.bangkit.genaidclean.ui.screen.user.detailbansos.UserDetailBansosViewModel
import com.bangkit.genaidclean.ui.screen.user.home.HomeViewModel
import com.bangkit.genaidclean.ui.screen.user.profile.UserProfileViewModel

class ViewModelFactory(private val repo : AppRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repo) as T
            }
            modelClass.isAssignableFrom(UserDetailBansosViewModel::class.java) -> {
                UserDetailBansosViewModel(repo) as T
            }
            modelClass.isAssignableFrom(CekBansosViewModel::class.java) -> {
                CekBansosViewModel(repo) as T
            }
            modelClass.isAssignableFrom(UserProfileViewModel::class.java) -> {
                UserProfileViewModel(repo) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
}