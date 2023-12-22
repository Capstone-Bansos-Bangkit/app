package com.bangkit.genaidclean.data.remote.response.user

import com.google.gson.annotations.SerializedName

data class PostQuestionAnswerResponse(

	@field:SerializedName("result")
	val result: ResultPostQuestionAnsw,

	@field:SerializedName("message")
	val message: String
)

data class ResultPostQuestionAnsw(

	@field:SerializedName("nik")
	val nik: String,

	@field:SerializedName("submission_id")
	val submissionId: Int,

	@field:SerializedName("answer")
	val answer: String,

	@field:SerializedName("question_id")
	val questionId: String
)
