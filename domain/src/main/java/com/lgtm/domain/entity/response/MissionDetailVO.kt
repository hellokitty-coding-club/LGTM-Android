package com.lgtm.domain.entity.response

data class MissionDetailVO(
    val currentPeopleNumber: Int,
    val description: String,
    val maxPeopleNumber: Int,
    val memberProfile: MemberProfileVO,
    val memberType: String,
    val missionId: Int,
    val missionRepositoryUrl: String,
    val missionStatus: String,
    val missionTitle: String,
    val notRecommendTo: String,
    val price: Int,
    val recommendTo: String,
    val registrationDueDate: String,
    val scraped: Boolean,
    val techTagList: List<TechTagVO>
)