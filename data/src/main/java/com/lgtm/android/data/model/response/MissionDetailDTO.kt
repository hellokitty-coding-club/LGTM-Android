package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.android.data.model.mapper.parseDate
import com.lgtm.domain.constants.MissionDetailStatus.Companion.getMissionDetailStatus
import com.lgtm.domain.constants.MissionStatus.Companion.getMissionStatus
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.MissionDetailVO

data class MissionDetailDTO(
    val currentPeopleNumber: Int?,
    val description: String?,
    val maxPeopleNumber: Int?,
    val memberProfile: ProfileDTO?,
    val memberType: String?,
    val missionId: Int?,
    val missionRepositoryUrl: String?,
    val missionStatus: String?,
    val missionTitle: String?,
    val notRecommendTo: String?,
    val price: Int?,
    val recommendTo: String?,
    val remainingRegisterDays: Int?,
    val scraped: Boolean?,
    val techTagList: List<TechTagDTO>?,
    @SerializedName("participated")
    val isParticipated: Boolean?,
    @SerializedName("closed")
    val isClosed: Boolean?,
    val createdAt: String?,
) {
    fun toVO(role: Role?): MissionDetailVO {
        val memberType: Role = Role.getRole(requireNotNull(memberType)) ?: requireNotNull(role)
        return MissionDetailVO(
            currentPeopleNumber = requireNotNull(currentPeopleNumber),
            description = requireNotNull(description),
            maxPeopleNumber = requireNotNull(maxPeopleNumber),
            memberProfile = requireNotNull(memberProfile).toVO(),
            memberType = memberType,
            missionId = requireNotNull(missionId),
            missionRepositoryUrl = requireNotNull(missionRepositoryUrl),
            missionStatus = getMissionStatus(missionStatus),
            missionTitle = requireNotNull(missionTitle),
            notRecommendTo = notRecommendTo,
            price = requireNotNull(price),
            recommendTo = recommendTo,
            remainingRegisterDays = requireNotNull(remainingRegisterDays),
            scraped = requireNotNull(scraped),
            techTagList = requireNotNull(techTagList).map { it.toVO() },
            missionDetailStatus = getMissionDetailStatus(
                memberType,
                requireNotNull(isParticipated),
                requireNotNull(isClosed)
            ),
            date = parseDate(createdAt)
        )
    }
}