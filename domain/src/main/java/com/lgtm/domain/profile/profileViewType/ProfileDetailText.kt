package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType


data class ProfileDetailText(
    val detail: String,
    val isCareer: Boolean = false,
    override val viewType: ProfileViewType = ProfileViewType.DETAIL_TEXT
) : Profile