package com.bangkit.genaidclean.data.remote.response.login

import com.bangkit.genaidclean.data.preferences.UserModel
import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @field:SerializedName("result")
    val result: Result,

    @field:SerializedName("message")
    val message: String,
)

data class Result(

    @field:SerializedName("payload")
    val payload: Payload,

    @field:SerializedName("token")
    val token: String,
){
    fun toUserModel() : UserModel {
        return UserModel(
            name = payload.name,
            role = payload.role,
            token = token,
        )
    }
}

data class Payload(

    @field:SerializedName("role")
    val role: String,

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("nik")
    val nik: String? = null,

    @field:SerializedName("birth_date")
    val birthDate: String? = null,
)
