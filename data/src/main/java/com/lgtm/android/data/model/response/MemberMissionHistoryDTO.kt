package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.MemberMissionHistoryVO

data class MemberMissionHistoryDTO(
    val missionId: Int?,
    val missionTitle: String?,
    val techTagList: List<TechTagDTO>?
) {
    fun toVO(): MemberMissionHistoryVO {
        return MemberMissionHistoryVO(
            missionId ?: 0,
            missionTitle ?: "Unknown",
            techTagList?.map { it.toVO() } ?: listOf()
        )
    }
}