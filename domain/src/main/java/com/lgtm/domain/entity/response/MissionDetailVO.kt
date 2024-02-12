package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.MissionDetailStatus
import com.lgtm.domain.constants.MissionStatus
import com.lgtm.domain.constants.Role
import java.time.LocalDateTime

data class MissionDetailVO(
    val currentPeopleNumber: Int,
    val description: String,
    val maxPeopleNumber: Int,
    val memberProfile: ProfileVO,
    val memberType: Role,
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
    val missionDetailStatus: MissionDetailStatus,
    val date: LocalDateTime?,
)