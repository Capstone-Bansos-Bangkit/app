package com.bangkit.genaidclean.data.di

import android.content.Context
import com.bangkit.genaidclean.data.preferences.UserPreferences
import com.bangkit.genaidclean.data.remote.api.ApiConfig
import com.bangkit.genaidclean.data.repository.AppRepository

object Inject {
    fun provideRepository(context: Context): AppRepository{
        val userPref = UserPreferences.getInstance(context)
        val apiService = ApiConfig.getApiService(context)

        return AppRepository.getInstance(userPref, apiService)
    }
}