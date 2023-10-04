package com.lgtm.domain.profile

data class ProfileImage(
    val profileImageUrl: String,
    val isMyProfile : Boolean,
    override val viewType: ProfileViewType = ProfileViewType.BIG_PROFILE_IMAGE,
) : Profile