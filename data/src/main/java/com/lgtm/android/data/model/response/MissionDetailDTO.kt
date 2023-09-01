package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.MissionDetailVO

data class MissionDetailDTO(
    val currentPeopleNumber: Int?,
    val description: String?,
    val maxPeopleNumber: Int?,
    val memberProfile: MemberProfileDTO?,
    val memberType: String?,
    val missionId: Int?,
    val missionRepositoryUrl: String?,
    val missionStatus: String?,
    val missionTitle: String?,
    val notRecommendTo: String?,
    val price: Int?,
    val recommendTo: String?,
    val registrationDueDate: String?,
    val scraped: Boolean?,
    val techTagList: List<TechTagDTO>?
){
    fun toVO() : MissionDetailVO{
        return MissionDetailVO(
            currentPeopleNumber = requireNotNull(currentPeopleNumber),
            description = requireNotNull(description),
            maxPeopleNumber = requireNotNull(maxPeopleNumber),
            memberProfile = requireNotNull(memberProfile).toVO(),
            memberType = requireNotNull(memberType),
            missionId = requireNotNull(missionId),
            missionRepositoryUrl = requireNotNull(missionRepositoryUrl),
            missionStatus = requireNotNull(missionStatus),
            missionTitle = requireNotNull(missionTitle),
            notRecommendTo = requireNotNull(notRecommendTo),
            price = requireNotNull(price),
            recommendTo = requireNotNull(recommendTo),
            registrationDueDate = requireNotNull(registrationDueDate),
            scraped = requireNotNull(scraped),
            techTagList = requireNotNull(techTagList).map { it.toVO() }
        )
    }
}