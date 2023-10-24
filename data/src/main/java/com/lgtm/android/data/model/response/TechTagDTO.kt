package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.TechTagVO

data class TechTagDTO(
    val name: String?,
    val techTagId: Int?,
    val iconImageUrl : String?
) {
    fun toVO(): TechTagVO {
        return TechTagVO(
            name = requireNotNull(name),
            techTagId = requireNotNull(techTagId),
            icon = requireNotNull(iconImageUrl)
        )
    }
}