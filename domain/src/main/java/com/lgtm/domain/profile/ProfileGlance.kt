package com.lgtm.domain.profile

import com.lgtm.domain.constants.Role

data class ProfileGlance(
    // Presentation Layer에서 ProfileGlanceUI 로 매핑
    val memberId: Int,
    val profileImage: String,
    val nickname: String,
    val githubId: String,
    // for junior
    val educationalHistory: String?,
    // for senior
    val position: String?,
    val company: String?,
    val careerPeriod: Int?,
    val name: String? = "서버 정보 없음",
    val memberType : Role,
    override val viewType: ProfileViewType = ProfileViewType.PROFILE_GLANCE
) : Profile