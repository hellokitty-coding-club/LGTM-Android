package com.lgtm.android.data.model.response

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.ProfileVO

data class ProfileDTO(
    val agreeWithEventInfo: Boolean?,
    val githubId: String?,
    val introduction: String?,
    val memberDetailInfo: MemberDetailInfoDTO?,
    val memberId: Int?,
    val memberMissionHistory: List<MemberMissionHistoryDTO>?,
    val memberType: String?,
    @SerializedName("nickName")
    val nickname: String?,
    val profileImageUrl: String?,
    val techTagList: List<TechTagDTO>?,
    val company: String?,
    val position: String?,
) {
    fun toVO(): ProfileVO {
        return ProfileVO(
            agreeWithEventInfo = agreeWithEventInfo ?: false,
            githubId = githubId ?: "unknown",
            introduction = introduction ?: "",
            memberId = memberId ?: 0,
            memberMissionHistory = memberMissionHistory?.map { it.toVO() },
            memberType = Role.getRole(memberType),
            nickname = nickname ?: "",
            profileImageUrl = profileImageUrl ?: "",
            techTagList = techTagList?.map { it.toVO() } ?: listOf(),
            educationalHistory = memberDetailInfo?.educationalHistory,
            position = position ?: memberDetailInfo?.position,
            company = company ?: memberDetailInfo?.companyInfo,
            careerPeriod = memberDetailInfo?.careerPeriod
        )
    }
}