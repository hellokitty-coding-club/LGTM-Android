package com.lgtm.android.data.model.response

import com.lgtm.domain.server_drive_ui.SectionItemVO

data class MemberMissionHistoryDTO(
    val missionId: Int?,
    val missionTitle: String?,
    val techTagList: List<TechTagDTO>?
) {
    fun toVO(): SectionItemVO {
        return SectionItemVO(
            missionId ?: 0,
            missionTitle ?: "Unknown",
            techTagList?.map { it.toVO() } ?: listOf()
        )
    }
}