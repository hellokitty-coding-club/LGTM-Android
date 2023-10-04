package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileTitleTextType
import com.lgtm.domain.profile.ProfileViewType

data class ProfileTitleText(
    val profileTitleTextType: ProfileTitleTextType,
    override val viewType: ProfileViewType = ProfileViewType.TITLE_TEXT
) : Profile
