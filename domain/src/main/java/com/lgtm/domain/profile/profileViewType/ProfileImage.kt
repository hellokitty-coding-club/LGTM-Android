package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType

data class ProfileImage(
    val profileImageUrl: String,
    val isMyProfile: Boolean,
    override val viewType: ProfileViewType = ProfileViewType.BIG_PROFILE_IMAGE,
) : Profile