package com.lgtm.domain.profile


data class ProfileDetailText(
    val detail: String,
    val isCareer: Boolean = false,
    override val viewType: ProfileViewType = ProfileViewType.DETAIL_TEXT
) : Profile