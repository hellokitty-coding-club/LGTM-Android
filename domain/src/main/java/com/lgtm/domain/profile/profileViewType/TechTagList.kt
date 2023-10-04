package com.lgtm.domain.profile.profileViewType

import com.lgtm.domain.entity.response.TechTagVO
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType

data class TechTagList(
    val techTagList: List<TechTagVO>,
    override val viewType: ProfileViewType = ProfileViewType.TECH_TAG_LIST
) : Profile