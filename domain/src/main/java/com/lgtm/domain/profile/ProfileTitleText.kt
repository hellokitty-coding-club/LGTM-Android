package com.lgtm.domain.profile

data class ProfileTitleText(
    val profileTitleTextType: ProfileTitleTextType,
    override val viewType: ProfileViewType = ProfileViewType.TITLE_TEXT
) : Profile
