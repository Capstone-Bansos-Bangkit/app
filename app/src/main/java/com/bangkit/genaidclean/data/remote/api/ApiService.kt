package com.bangkit.genaidclean.data.remote.api

import com.bangkit.genaidclean.data.remote.response.Bansos
import com.bangkit.genaidclean.data.remote.response.login.LoginResponse
import com.bangkit.genaidclean.data.remote.response.user.StatusBansosResponse
import com.bangkit.genaidclean.data.remote.response.user.UserProfileResponse
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST


interface ApiService {

    //AUTH
    @FormUrlEncoded
    @POST("login/admin")
    suspend fun loginAdmin(
        @Field("username") username : String,
        @Field("password") password : String
    ): LoginResponse

    @FormUrlEncoded
    @POST("login/user")
    suspend fun loginUser(
        @Field("nik") nik: String,
        @Field("mother_name") motherName: String,
        @Field("birth_date")  birthDate: String,
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



    //ADMIN



}