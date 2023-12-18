package com.bangkit.genaidclean.data.remote.response

import com.google.gson.annotations.SerializedName

data class Bansos(

    @field:SerializedName("result")
    val result: List<ResultItem>,

    @field:SerializedName("message")
    val message: String,
)

data class ResultItem(

    @field:SerializedName("logo_url")
    val logoUrl: String = "",

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("description")
    val description: String = "",

    @field:SerializedName("alias")
    val alias: String,

    @field:SerializedName("total_penerima")
    val totalPenerima: Int = 0,

    @field:SerializedName("total_periode")
    val totalPeriode: Int = 0,

    ) {

}
