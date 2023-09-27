package com.lgtm.domain.profile

data class ProfileTitleText(
    val title: String,
    override val viewType: ProfileViewType = ProfileViewType.TITLE_TEXT
) : Profile