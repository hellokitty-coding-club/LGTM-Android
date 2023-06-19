package com.lgtm.android.data.remote.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.entity.response.IntroVO

data class IntroDTO(
    @SerializedName("minVersion")
    val minVersion: Int?,
    @SerializedName("latestVersion")
    val latestVersion: Int?
) {
    fun toVO() = IntroVO(
        minVersion = minVersion ?: 0,
        latestVersion = latestVersion ?: 0
    )
}
