package com.bangkit.genaidclean.data.remote.response.admin

import com.bangkit.genaidclean.data.models.DataSubmission
import com.google.gson.annotations.SerializedName

data class SubmissionListResponse(

    @field:SerializedName("result")
    val result: List<ResultItem>,

    @field:SerializedName("total")
    val total: Int,

    @field:SerializedName("message")
    val message: String,
)

data class ResultItem(

    @field:SerializedName("desa")
    val desa: String = "",

    @field:SerializedName("bansos_provider_name")
    val bansosProviderName: String = "",

    @field:SerializedName("bansos_provider_id")
    val bansosProviderId: Int = 0,

    @field:SerializedName("kec")
    val kec: String = "",

    @field:SerializedName("created_at")
    val createdAt: String = "",

    @field:SerializedName("kab")
    val kab: String = "",

    @field:SerializedName("alamat")
    val alamat: String = "",

    @field:SerializedName("bansos_provider_alias")
    val bansosProviderAlias: String = "",

    @field:SerializedName("bansos_event_periode")
    val bansosEventPeriode: String = "",

    @field:SerializedName("submission_id")
    val submissionId: Int = 0,

    @field:SerializedName("nik")
    val nik: String = "",

    @field:SerializedName("bansos_event_id")
    val bansosEventId: Int = 0,

    @field:SerializedName("name")
    val name: String = "",

    @field:SerializedName("prov")
    val prov: String = "",

    @field:SerializedName("status")
    val status: String = "",
) {
    fun toDataSubmission(): DataSubmission {
        return DataSubmission(
            desa = desa,
            bansosProviderName = bansosProviderName,
            bansosProviderId = bansosProviderId,
            kec = kec,
            createdAt = createdAt,
            kab = kab,
            alamat = alamat,
            bansosProviderAlias = bansosProviderAlias,
            bansosEventPeriode = bansosEventPeriode,
            submissionId = submissionId,
            nik = nik,
            bansosEventId = bansosEventId,
            name = name,
            prov = prov,
            status = status,
        )
    }
}
