package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.ProfileVO

data class ProfileDTO(
    val agreeWithEventInfo: Boolean?,
    val githubId: String?,
    val introduction: String?,
    val memberDetailInfo: MemberDetailInfoDTO?,
    val memberId: Int?,
    val memberMissionHistory: List<MemberMissionHistoryDTO>?,
    val memberType: String?,
    val nickName: String?,
    val profileImageUrl: String?,
    val techTagList: List<TechTagDTO>?
) {
    fun toVO(): ProfileVO {
        return ProfileVO(
            agreeWithEventInfo ?: false,
            githubId ?: "",
            introduction ?: "",
            memberDetailInfo?.toVO() ?: throw IllegalStateException("memberDetailInfo must not be null"),
            memberId ?: 0,
            memberMissionHistory?.map { it.toVO() },
            memberType ?: "",
            nickName ?: "",
            profileImageUrl ?: "",
            techTagList?.map { it.toVO() } ?: listOf()
        )
    }
}