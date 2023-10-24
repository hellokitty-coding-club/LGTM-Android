package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType

data class ThinDivider(
    override val viewType: ProfileViewType = ProfileViewType.THIN_DIVIDER
) : Profile