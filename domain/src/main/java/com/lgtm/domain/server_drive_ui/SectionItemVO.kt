package com.lgtm.domain.server_drive_ui

data class SectionItemVO(
    // 진행 중인 미션, 추천 미션, 전체 미션
    val missionId: Int,
    val missionTitle: String,
    val techTagList: List<String>,

    // 추천 미션, 전체 미션
    val currentPeopleNumber: Int? = null,
    val maxPeopleNumber: Int? = null,
    val price: Int? = null,
    val remainingRegisterDays: Int? = null,
    val scrapCount: Int? = null,
    val scraped: Boolean? = null,
    val viewCount: Int? = null,
)