package com.lgtm.domain.profile


data class ProfileDetailText(
    val detail: String,
    override val viewType: ProfileViewType = ProfileViewType.DETAIL_TEXT
) : Profile