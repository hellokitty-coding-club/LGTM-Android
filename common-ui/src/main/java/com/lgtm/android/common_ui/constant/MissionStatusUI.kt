package com.lgtm.android.common_ui.constant

import androidx.annotation.ColorRes
import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.MissionStatus

enum class MissionStatusUI(
    private val missionStatus: MissionStatus,
    val message: String,
    @ColorRes val color: Int
) {
    RECRUITING(MissionStatus.RECRUITING, "참가자 모집중", R.color.green),
    MISSION_PROCEEDING(MissionStatus.MISSION_PROCEEDING, "미션 진행중", R.color.yellow),
    MISSION_FINISHED(MissionStatus.MISSION_FINISHED, "미션 종료", R.color.gray_6);

    companion object {
        fun getMissionStatusUI(missionStatus: MissionStatus): MissionStatusUI {
            return values().find { it.missionStatus == missionStatus }
                ?: throw IllegalArgumentException("Invalid Mission Status: $missionStatus")
        }
    }
}