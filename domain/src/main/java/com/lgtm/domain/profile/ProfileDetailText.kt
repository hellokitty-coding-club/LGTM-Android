package com.lgtm.domain.profile


data class ProfileDetailText(
    val detail: String,
    override val viewType: ProfileViewType = ProfileViewType.PROFILE_DETAIL_TEXT
) : Profile