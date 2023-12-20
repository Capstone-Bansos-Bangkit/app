package com.bangkit.genaidclean.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangkit.genaidclean.AdminActivityViewModel
import com.bangkit.genaidclean.AuthViewModel
import com.bangkit.genaidclean.MainViewModel
import com.bangkit.genaidclean.data.di.Inject
import com.bangkit.genaidclean.data.repository.AppRepository
import com.bangkit.genaidclean.ui.screen.admin.bansos.AdminBansosViewModel
import com.bangkit.genaidclean.ui.screen.admin.dashboard.DashboardAdminViewModel
import com.bangkit.genaidclean.ui.screen.admin.verifikasi.VerifikasiViewModel
import com.bangkit.genaidclean.ui.screen.user.cekbansos.CekBansosViewModel
import com.bangkit.genaidclean.ui.screen.user.detailbansos.UserDetailBansosViewModel
import com.bangkit.genaidclean.ui.screen.user.home.HomeViewModel
import com.bangkit.genaidclean.ui.screen.user.pengajuan.UserPengajuanViewModel
import com.bangkit.genaidclean.ui.screen.user.profile.UserProfileViewModel
import com.bangkit.genaidclean.ui.screen.user.question.QuestionViewModel

class ViewModelFactory(private val repo : AppRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            // ACTIVITY \\
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repo) as T
            }
            modelClass.isAssignableFrom(AuthViewModel::class.java) -> {
                AuthViewModel(repo) as T
            }
            modelClass.isAssignableFrom(AdminActivityViewModel::class.java) -> {
                AdminActivityViewModel(repo) as T
            }

            // USER \\
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
            modelClass.isAssignableFrom(UserPengajuanViewModel::class.java) -> {
                UserPengajuanViewModel(repo) as T
            }
            modelClass.isAssignableFrom(QuestionViewModel::class.java) -> {
                QuestionViewModel(repo) as T
            }

            // ADMIN \\
            modelClass.isAssignableFrom(DashboardAdminViewModel::class.java) -> {
                DashboardAdminViewModel(repo) as T
            }
            modelClass.isAssignableFrom(VerifikasiViewModel::class.java) -> {
                VerifikasiViewModel(repo) as T
            }
            modelClass.isAssignableFrom(AdminBansosViewModel::class.java) -> {
                AdminBansosViewModel(repo) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }
    companion object{
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        fun getInstance(context: Context): ViewModelFactory{
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class) {
                    INSTANCE = ViewModelFactory(
                        Inject.provideRepository(context)
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}