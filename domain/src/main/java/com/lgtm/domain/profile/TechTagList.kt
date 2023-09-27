package com.lgtm.domain.profile

import com.lgtm.domain.entity.response.TechTagVO

data class TechTagList(
    val techTagList: List<TechTagVO>,
    override val viewType: ProfileViewType = ProfileViewType.TECH_TAG_LIST
) : Profile