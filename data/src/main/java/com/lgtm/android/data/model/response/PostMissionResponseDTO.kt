package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.PostMissionResponseVO

data class PostMissionResponseDTO(
    val writerId: Int?,
    val missionId: Int?
) {
    @Throws(IllegalArgumentException::class)
    fun toVO() = PostMissionResponseVO(
        writerId = requireNotNull(writerId),
        missionId = requireNotNull(missionId)
    )
}
