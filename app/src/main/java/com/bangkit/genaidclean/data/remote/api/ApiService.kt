package com.bangkit.genaidclean.data.remote.api

import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.admin.ResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionSummaryResponse
import com.bangkit.genaidclean.data.remote.response.login.LoginResponse
import com.bangkit.genaidclean.data.remote.response.user.QuestionResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query


interface ApiService {

    //AUTH

    @POST("login/admin")
    suspend fun loginAdmin(
        @Query("username") username : String,
        @Query("password") password : String
    ): LoginResponse

    @POST("login/user")
    suspend fun loginUser(
        @Query("nik") nik: String,
        @Query("mother_name") motherName: String,
        @Query("birth_date")  birthDate: String,
    ) : LoginResponse


    // BOTH ROLE

    @GET("bansos")
    suspend fun getListBansos(
    ): Bansos

    //USER
    @GET("user/profile")
    suspend fun getUserProfile(
    ): UserProfileResponse

    @GET("submission/latest_status")
    suspend fun getStatusName(
    ): StatusBansosResponse

    @GET("questioner")
    suspend fun getQuestions(
    ): QuestionResponse



    //ADMIN
    @GET("admin/submission/summary")
    suspend fun getSubmissionSummary(
    ): SubmissionSummaryResponse

    @GET("admin/submission/list")
    suspend fun getApprovedSubmissionList(
        @Query("status")status: String = "approved"
    ): SubmissionListResponse

    @GET("admin/submission/list")
    suspend fun getPendingSubmissionList(
        @Query("status")status: String = "pending",
    ): SubmissionListResponse



}