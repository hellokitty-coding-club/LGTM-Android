package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.SduiItemVO
import com.lgtm.domain.entity.response.SduiVO

data class SduiDTO(
    val screenName: String?, val contents: List<SduiItemVO>?
)

fun SduiDTO.toVO(): SduiVO {
    return SduiVO(
        sceneName = this.screenName ?: "Unknown",
        contents = if (!this.contents.isNullOrEmpty()) this.contents else emptyList()
    )
}