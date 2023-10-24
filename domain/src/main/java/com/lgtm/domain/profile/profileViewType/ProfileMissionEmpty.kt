package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType

data class ProfileMissionEmpty(
    override val viewType: ProfileViewType = ProfileViewType.MISSION_EMPTY
) : Profile