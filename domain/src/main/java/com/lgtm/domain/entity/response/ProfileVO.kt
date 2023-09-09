package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.Role

data class ProfileVO(
    val agreeWithEventInfo: Boolean?,
    val githubId: String,
    val introduction: String,
    val memberId: Int,
    val memberType: Role?,
    val nickname: String,
    val profileImageUrl: String,
    val techTagList: List<TechTagVO>,
    val memberMissionHistory: List<MemberMissionHistoryVO>?,
    // for junior
    val educationalHistory: String?,
    // for senior
    val position: String?,
    val company: String?,
    val careerPeriod: Int?,
)