package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType

data class ThickDivider(
    override val viewType: ProfileViewType = ProfileViewType.THICK_DIVIDER
) : Profile