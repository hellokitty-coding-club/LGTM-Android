package com.lgtm.domain.entity.response

data class ProfileVO(
    val agreeWithEventInfo: Boolean,
    val githubId: String,
    val introduction: String,
    val memberDetailInfo: MemberDetailInfoVO,
    val memberId: Int,
    val memberMissionHistory: List<MemberMissionHistoryVO>?,
    val memberType: String,
    val nickName: String,
    val profileImageUrl: String,
    val techTagList: List<TechTagVO>
)