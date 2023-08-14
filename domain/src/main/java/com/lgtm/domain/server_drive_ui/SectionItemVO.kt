package com.lgtm.domain.server_drive_ui

import com.google.gson.annotations.SerializedName

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
    @SerializedName("scraped")
    val isScraped: Boolean? = null,
) : SduiContent

