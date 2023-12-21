package com.bangkit.genaidclean.data.remote.response.admin

import com.google.gson.annotations.SerializedName

data class SubmissionDetailResponse(

	@field:SerializedName("result")
	val result: DetailSubmissionResult,

	@field:SerializedName("message")
	val message: String
)

data class QuestionerResultItem(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("answer")
	val answer: String
)

data class AttachmentResultItem(

	@field:SerializedName("question")
	val question: String,

	@field:SerializedName("answer")
	val answer: String
)

data class DetailSubmissionResult(

	@field:SerializedName("desa")
	val desa: String,

	@field:SerializedName("bansos_provider_name")
	val bansosProviderName: String,

	@field:SerializedName("ml_result")
	val mlResult: String,

	@field:SerializedName("questioner_result")
	val questionerResult: List<QuestionerResultItem>,

	@field:SerializedName("kec")
	val kec: String,

	@field:SerializedName("kab")
	val kab: String,

	@field:SerializedName("alamat")
	val alamat: String,

	@field:SerializedName("attachment_result")
	val attachmentResult: List<AttachmentResultItem>,

	@field:SerializedName("nik")
	val nik: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("phone_number")
	val phoneNumber: String?,

	@field:SerializedName("no_kk")
	val noKk: String,

	@field:SerializedName("prov")
	val prov: String,

	@field:SerializedName("email")
	val email: String?,

	@field:SerializedName("status")
	val status: String
)
