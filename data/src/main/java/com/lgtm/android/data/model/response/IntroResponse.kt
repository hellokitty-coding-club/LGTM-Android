package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.constants.VERSION_UNKNOWN
import com.lgtm.domain.entity.response.IntroVO

data class IntroResponse(
    @SerializedName("minVersion")
    val minVersion: Int?,
    @SerializedName("latestVersion")
    val latestVersion: Int?
) {
    fun toVO() = IntroVO(
        minVersion = minVersion ?: VERSION_UNKNOWN,
        latestVersion = latestVersion ?: VERSION_UNKNOWN
    )
}
