package com.bangkit.genaidclean.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class UpdateProfileResponse(

	@field:SerializedName("result")
	val result: UpdateProfileResult,

	@field:SerializedName("message")
	val message: String
)

data class UpdateProfileResult(

	@field:SerializedName("nik")
	val nik: String?,

	@field:SerializedName("address")
	val address: String?,

	@field:SerializedName("birth_date")
	val birthDate: String?,

	@field:SerializedName("phone_number")
	val phoneNumber: String?,

	@field:SerializedName("profile_pic_url")
	val profilePicUrl: String?,

	@field:SerializedName("email")
	val email: String?,
)
