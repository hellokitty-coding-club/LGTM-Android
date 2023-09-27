package com.lgtm.domain.profile

data class ProfileImage(
    val profileImageUrl: String,
    override val viewType: ProfileViewType = ProfileViewType.BIG_PROFILE_IMAGE,
) : Profile