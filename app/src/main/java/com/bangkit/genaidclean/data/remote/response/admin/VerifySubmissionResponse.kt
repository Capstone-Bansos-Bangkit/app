package com.bangkit.genaidclean.data.remote.response.admin

import com.google.gson.annotations.SerializedName

data class VerifySubmissionResponse(

	@field:SerializedName("result")
	val result: VerifyResult?,

	@field:SerializedName("message")
	val message: String
)

data class VerifyResult(

	@field:SerializedName("nik")
	val nik: String,

	@field:SerializedName("bansos_event")
	val bansosEvent: String,

	@field:SerializedName("status")
	val status: String
)
