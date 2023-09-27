package com.lgtm.domain.profile

data class ProfileImage(
    val profileImageUrl: String,
    override val viewType: ProfileViewType = ProfileViewType.PROFILE_IMAGE,
) : Profile