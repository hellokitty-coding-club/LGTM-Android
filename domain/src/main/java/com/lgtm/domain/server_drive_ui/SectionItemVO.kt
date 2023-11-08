package com.lgtm.domain.server_drive_ui

import com.google.gson.annotations.SerializedName
import com.lgtm.domain.entity.response.TechTagVO
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileViewType
import com.lgtm.domain.profile.ProfileViewType.*

data class SectionItemVO(
    // 진행 중인 미션, 추천 미션, 전체 미션
    val missionId: Int,
    val missionTitle: String,
    val techTagList: List<TechTagVO>,

    // 추천 미션, 전체 미션
    val remainingRegisterDays: Int? = null,
    val viewCount: Int? = null,
    val price: Int? = null,
    val currentPeopleNumber: Int? = null,
    val maxPeopleNumber: Int? = null,
    val scrapCount: Int? = null,
    val missionCategory : String? = null,
    @SerializedName("scraped")
    val isScraped: Boolean? = null,
    override val viewType: ProfileViewType = SECTION_ITEM_VO
) : SduiContent, Profile

