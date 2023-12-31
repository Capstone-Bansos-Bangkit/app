package com.bangkit.genaidclean.data.remote.api

import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.admin.ResultItem
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionDetailResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionListResponse
import com.bangkit.genaidclean.data.remote.response.admin.SubmissionSummaryResponse
import com.bangkit.genaidclean.data.remote.response.admin.VerifySubmissionResponse
import com.bangkit.genaidclean.data.remote.response.login.LoginResponse
import com.bangkit.genaidclean.data.remote.response.user.PostQuestionAnswerResponse
import com.bangkit.genaidclean.data.remote.response.user.PostSubmissionResponse
import com.bangkit.genaidclean.data.remote.response.user.QuestionResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.UpdateProfileResponse
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Query


interface ApiService {

    //AUTH
    @POST("login/dev_user")
    suspend fun loginDevUser() : LoginResponse

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

    @PUT("user/profile")
    suspend fun updateEmail(
        @Query("email") email: String
    ):UpdateProfileResponse

    @PUT("user/profile")
    suspend fun updatePhone(
        @Query("phone_number") phoneNumber: String
    ):UpdateProfileResponse

    @PUT("submission/answer")
    suspend fun submitAnswer(
        @Query("submission_id")submissionId: Int,
        @Query("question_id")questionId: Int,
        @Query("answer")answer: String,
    ):PostQuestionAnswerResponse

    @PUT("submission/submit")
    suspend fun submitSubmission(
        @Query("submission_id")submissionId: Int
    ):PostSubmissionResponse



    //ADMIN
    @GET("admin/submission/summary")
    suspend fun getSubmissionSummary(
    ): SubmissionSummaryResponse

    @GET("admin/submission/list")
    suspend fun getApprovedSubmissionList(
        @Query("status")status: String = "approved",
        @Query("limit")limit: Int = 30
    ): SubmissionListResponse

    @GET("admin/submission/list")
    suspend fun getPendingSubmissionList(
        @Query("bansos_provider_id") bansosId: Int? = null,
        @Query("status")status: String = "pending",
        @Query("limit")limit: Int = 50
    ): SubmissionListResponse


    @GET("admin/submission/detail")
    suspend fun getSubmissionDetail(
        @Query("user_submission_id")submissionId: Int
    ): SubmissionDetailResponse


    // approve/disapprove submission
    @PUT("admin/submission/approve")
    suspend fun verifySubmission(
        @Query("user_submission_id")submissionId: Int,
        @Query("status")status: String
    ):VerifySubmissionResponse

}