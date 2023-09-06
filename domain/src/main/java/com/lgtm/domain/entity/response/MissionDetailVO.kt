package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.MissionDetailStatus
import com.lgtm.domain.constants.MissionStatus

data class MissionDetailVO(
    val currentPeopleNumber: Int,
    val description: String,
    val maxPeopleNumber: Int,
    val memberProfile: MemberProfileVO,
    val memberType: String,
    val missionId: Int,
    val missionRepositoryUrl: String,
    val missionStatus: MissionStatus,
    val missionTitle: String,
    val notRecommendTo: String?,
    val price: Int,
    val recommendTo: String?,
    val remainingRegisterDays: Int,
    val scraped: Boolean,
    val techTagList: List<TechTagVO>,
    val missionDetailStatus: MissionDetailStatus
)