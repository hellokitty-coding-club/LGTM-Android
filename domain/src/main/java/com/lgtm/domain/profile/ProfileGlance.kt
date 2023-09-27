package com.lgtm.domain.profile

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
    override val viewType: ProfileViewType = ProfileViewType.PROFILE_GLANCE
) : Profile