package com.bangkit.genaidclean.data.repository

import androidx.lifecycle.liveData
import com.bangkit.genaidclean.data.models.DataBansos
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.data.preferences.UserPreferences
import com.bangkit.genaidclean.data.remote.api.ApiService
import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.remote.response.login.LoginResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusListItem
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.flow.Flow

class AppRepository private constructor(
    private val userPreferences: UserPreferences,
    private val apiService: ApiService
){

    // === === === === === ===  AUTH === === === === === \\
    fun loginUser(
        nik: String,
        motherName: String,
        birthDate: String
    ) = liveData {
        emit(State.Loading)
        try {
            val response = apiService.loginUser(nik, motherName, birthDate)
            emit(State.Success(response.result))
        } catch (e: Exception) {
            emit(State.Error(e.message))
        }
    }

    fun loginAdmin(
        username: String,
        password: String,
    ) = liveData {
        emit(State.Loading)
        try {
            val response = apiService.loginAdmin(username, password)
            emit(State.Success(response.result))
        } catch (e: Exception) {
            emit(State.Error(e.message))
        }
    }

    suspend fun saveSession(user: UserModel) {
        userPreferences.saveUserPref(user)
    }


    suspend fun clearSession() {
        userPreferences.clearUserPref()
    }

    fun getSession(): Flow<UserModel> = userPreferences.getUserPref()



    // === === === === === ===  USER === === === === === \\
    suspend fun getUserProfile(): UserProfileResponse {
        return apiService.getUserProfile()
    }

    suspend fun getStatusName(): StatusBansosResponse {
        return apiService.getStatusName()
    }

    suspend fun getStatusBansos(): List<StatusListItem> {
        val statusListResponse = apiService.getStatusName().result?.statusList
        return statusListResponse?.filterNotNull() ?: emptyList()
    }

    suspend fun getAllProfBansos(): List<ResultItem> {
        return try {
            val response = apiService.getListBansos()
            response.result.map { it }
        } catch (e: Exception) {
            emptyList()
        }
    }

    //filter berdasar nama
    suspend fun getPrfileBansosName(bansosName: String): List<ResultItem> {
        return getAllProfBansos().filter { it.name == bansosName }
    }



    // === === === === === ===  ADMIN === === === === === \\


    companion object {
        @Volatile
        private var instance: AppRepository? = null
        fun getInstance(
            userPreferences: UserPreferences,
            apiService: ApiService
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(userPreferences, apiService)
            }.also { instance = it }
    }

}