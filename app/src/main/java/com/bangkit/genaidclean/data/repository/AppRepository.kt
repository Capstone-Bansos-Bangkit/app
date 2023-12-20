package com.bangkit.genaidclean.data.repository

import android.util.Log
import androidx.lifecycle.liveData
import com.bangkit.genaidclean.data.models.DataBansos
import com.bangkit.genaidclean.data.models.DataSubmission
import com.bangkit.genaidclean.data.preferences.UserModel
import com.bangkit.genaidclean.data.preferences.UserPreferences
import com.bangkit.genaidclean.data.remote.api.ApiService
import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.ResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionSummaryResponse
import com.bangkit.genaidclean.data.remote.response.login.LoginResponse
import com.bangkit.genaidclean.data.remote.response.user.QuestionResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusListItem
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import com.bangkit.genaidclean.utils.State
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository private constructor(
    private val userPreferences: UserPreferences,
    private val apiService: ApiService
){

    // === === === === === ===  AUTH === === === === === \\
    suspend fun loginUser(
        nik: String,
        motherName: String,
        birthDate: String
    ): Flow<State<UserModel>> = flow {
        emit(State.Loading)
        try {
            val response = apiService.loginUser(nik, motherName, birthDate)
            val userData = response.result.toUserModel()
            emit(State.Success(userData))
        } catch (e: Exception) {
            emit(State.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    suspend fun loginAdmin(
        username: String,
        password: String
    ): Flow<State<UserModel>> = flow {
        emit(State.Loading)
        try {
            val response = apiService.loginAdmin(username, password)
            val userData = response.result.toUserModel()
            emit(State.Success(userData))
        } catch (e: Exception) {
            emit(State.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)


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
    suspend fun getBansosById(bansosId: Int): List<ResultItem> {
        return getAllProfBansos().filter { it.bansosProviderId == bansosId  }
    }

    suspend fun getQuestions(): QuestionResponse {
        return apiService.getQuestions()
    }



    // === === === === === ===  ADMIN === === === === === \\

    fun getSubmissionSummary(): Flow<SubmissionSummaryResponse> {
        return flow {
            emit(apiService.getSubmissionSummary())
        }.catch { e ->
            Log.d("AppRepository", "getSubmissionSummary: ${e.message}")
            e.printStackTrace()
        }
    }

    fun getApprovedSubmissionList(): Flow<SubmissionListResponse> {
        return flow {
            try {
                emit(apiService.getApprovedSubmissionList())
            } catch (e: Exception) {
                Log.d("AppRepository", "getSubmissionSummary: ${e.message}")
                e.printStackTrace()
                emit(SubmissionListResponse(emptyList(), 0, ""))
            }
        }
    }

    fun getPendingSubmissionList(): Flow<SubmissionListResponse> {
        return flow {
            try {
                emit(apiService.getPendingSubmissionList())
            } catch (e: Exception) {
                Log.d("AppRepository", "getSubmissionSummary: ${e.message}")
                e.printStackTrace()
                emit(SubmissionListResponse(emptyList(), 0, ""))
            }
        }
    }

    fun getDatabansos(): Flow<Bansos> {
        return flow {
            try {
                emit(apiService.getListBansos())
            } catch (e: Exception) {
                Log.d("AppRepository", "getSubmissionSummary: ${e.message}")
                e.printStackTrace()
                emit(Bansos(message = "Error Getting Bansos from the API", result = emptyList()))
            }
        }
    }

    suspend fun getBansosDetail(id: Int): ResultItem{
        return getAllProfBansos().filter { it.bansosProviderId == id }.first()
    }


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