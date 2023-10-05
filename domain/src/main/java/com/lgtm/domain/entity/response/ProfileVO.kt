package com.lgtm.domain.entity.response

import com.lgtm.domain.constants.Role
import com.lgtm.domain.server_drive_ui.SectionItemVO

data class ProfileVO(
    val isMyProfile : Boolean,
    val agreeWithEventInfo: Boolean?,
    val githubId: String,
    val introduction: String,
    val memberId: Int,
    val memberType: Role?,
    val nickname: String,
    val profileImageUrl: String,
    val techTagList: List<TechTagVO>,
    val memberMissionHistory: List<SectionItemVO>?,
    // for junior
    val educationalHistory: String?,
    // for senior
    val position: String?,
    val company: String?,
    val careerPeriod: Int?,
)