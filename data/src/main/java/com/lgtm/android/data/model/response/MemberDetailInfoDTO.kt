package com.lgtm.android.data.model.response

import com.lgtm.domain.entity.response.MemberDetailInfoVO

data class MemberDetailInfoDTO(
    val educationalHistory: String?,
    val companyInfo: String?,
    val careerPeriod: Int?,
    val position: String?
) {
    fun toVO(): MemberDetailInfoVO {
        return MemberDetailInfoVO(
            companyInfo,
            careerPeriod,
            position,
            educationalHistory
        )
    }
}