package com.lgtm.android.common_ui.model.mapper

import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.constant.MissionDetailButtonStatus.Companion.getButtonStatusUI
import com.lgtm.android.common_ui.constant.MissionStatusUI.Companion.getMissionStatusUI
import com.lgtm.android.common_ui.constant.ProcessStateUI.Companion.getProcessStateUI
import com.lgtm.android.common_ui.model.DashboardUI
import com.lgtm.android.common_ui.model.MemberMissionStatusUI
import com.lgtm.android.common_ui.model.MissionDetailUI
import com.lgtm.android.common_ui.model.ProfileGlanceUI
import com.lgtm.domain.constants.Role
import com.lgtm.domain.constants.UNKNOWN
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.entity.response.MemberMissionStatusVO
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.entity.response.ProfileVO
import com.lgtm.domain.profile.ProfileGlance

fun MissionDetailVO.toUiModel(): MissionDetailUI = MissionDetailUI(
    currentPeopleNumber = currentPeopleNumber,
    description = description,
    maxPeopleNumber = maxPeopleNumber,
    memberProfile = memberProfile.toUiModel(memberType),
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

fun ProfileVO.toUiModel(role: Role): ProfileGlanceUI = ProfileGlanceUI(
    memberId = memberId,
    profileImage = profileImageUrl,
    nickname = nickname,
    githubId = githubId,
    detailInfoLabel = when (role) {
        Role.REVIEWER -> R.string.company_slash_position
        Role.REVIEWEE -> R.string.education
    },
    detailInfo = when (role) {
        Role.REVIEWER -> "$company / $position"
        Role.REVIEWEE -> educationalHistory
    } ?: UNKNOWN
)

fun ProfileGlance.toUiModel(): ProfileGlanceUI = ProfileGlanceUI(
    memberId = memberId,
    profileImage = profileImage,
    nickname = nickname,
    githubId = githubId,
    detailInfoLabel = when (memberType) {
        Role.REVIEWER -> R.string.company_slash_position
        Role.REVIEWEE -> R.string.education
    },
    detailInfo = when (memberType) {
        Role.REVIEWER -> "$company / $position"
        Role.REVIEWEE -> educationalHistory
    } ?: UNKNOWN
)

fun DashboardVO.toUiModel() = DashboardUI(
    memberInfoList = memberInfoList.map { it.toUiModel() },
    missionName = missionName,
    techTagList = techTagList
)

fun MemberMissionStatusVO.toUiModel() = MemberMissionStatusUI(
    githubId = githubId,
    githubPrUrl = githubPrUrl,
    memberId = memberId,
    nickname = nickname,
    missionFinishedDate = missionFinishedDate,
    paymentDate = paymentDate,
    processStatus = getProcessStateUI(processStatus),
    profileImageUrl = profileImageUrl,
    isMissionSubmitted = isMissionSubmitted,
)