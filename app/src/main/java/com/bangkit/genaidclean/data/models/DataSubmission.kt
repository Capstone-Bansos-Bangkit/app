package com.bangkit.genaidclean.data.models

import com.google.gson.annotations.SerializedName

data class DataSubmission(
    val desa: String,
    val bansosProviderName: String,
    val bansosProviderId: Int,
    val kec: String,
    val createdAt: String,
    val kab: String,
    val alamat: String,
    val bansosProviderAlias: String,
    val bansosEventPeriode: String,
    val submissionId: Int,
    val nik: String,
    val bansosEventId: Int,
    val name: String,
    val prov: String,
    val status: String
)
