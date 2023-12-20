package com.bangkit.genaidclean.data.remote.response.admin

import com.google.gson.annotations.SerializedName

data class SubmissionSummaryResponse(

	@field:SerializedName("result")
	val result: Result,

	@field:SerializedName("message")
	val message: String
)

data class Result(

	@field:SerializedName("total_not_eligible")
	val totalNotEligible: Int,

	@field:SerializedName("total_eligible")
	val totalEligible: Int,

	@field:SerializedName("total_all")
	val totalAll: Int,

	@field:SerializedName("per_bansos")
	val perBansos: List<PerBansosItem>,

)

data class PerBansosItem(

	@field:SerializedName("bansos_name")
	val bansosName: String,

	@field:SerializedName("total_eligible")
	val totalEligible: Int,

)
