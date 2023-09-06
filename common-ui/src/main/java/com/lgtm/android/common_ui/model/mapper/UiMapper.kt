package com.lgtm.android.common_ui.model.mapper

import com.lgtm.android.common_ui.constant.MissionDetailButtonStatus.Companion.getButtonStatusUI
import com.lgtm.android.common_ui.constant.MissionStatusUI.Companion.getMissionStatusUI
import com.lgtm.android.common_ui.model.MissionDetailUiState
import com.lgtm.domain.entity.response.MissionDetailVO

fun MissionDetailVO.toUiModel(): MissionDetailUiState = MissionDetailUiState(
    currentPeopleNumber = currentPeopleNumber,
    description = description,
    maxPeopleNumber = maxPeopleNumber,
    memberProfile = memberProfile,
    memberType = memberType,
    missionId = missionId,
    missionRepositoryUrl = missionRepositoryUrl,
    missionStatus = getMissionStatusUI(missionStatus),
    missionTitle = missionTitle,
    notRecommendTo = notRecommendTo,
    price = price,
    recommendTo = recommendTo,
    remainingRegisterDays = remainingRegisterDays,
    scraped = scraped,
    techTagList = techTagList,
    missionDetailButtonStatusUI = getButtonStatusUI(missionDetailStatus)
)