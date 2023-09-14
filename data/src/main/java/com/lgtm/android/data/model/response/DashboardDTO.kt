package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.DashboardVO

data class DashboardDTO(
    val memberInfoList: List<MemberMissionStatusDTO>?,
    val missionName: String?,
    val techTagList: List<TechTagDTO>?
) {
    fun toVO(): DashboardVO {
        return DashboardVO(
            memberInfoList = memberInfoList?.map { it.toVO() } ?: listOf(),
            missionName = requireNotNull(missionName),
            techTagList = techTagList?.map { it.toVO() } ?: listOf()
        )
    }
}