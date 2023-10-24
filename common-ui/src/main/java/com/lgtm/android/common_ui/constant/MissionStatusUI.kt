package com.lgtm.android.common_ui.constant

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.MissionStatus

enum class MissionStatusUI(
    private val missionStatus: MissionStatus,
    @StringRes val message: Int,
    @ColorRes val color: Int
) {
    RECRUITING(MissionStatus.RECRUITING, R.string.mission_status_recruiting, R.color.green),
    MISSION_PROCEEDING(MissionStatus.MISSION_PROCEEDING, R.string.mission_proceeding, R.color.yellow),
    MISSION_FINISHED(MissionStatus.MISSION_FINISHED, R.string.mission_finished, R.color.gray_6);

    companion object {
        fun getMissionStatusUI(missionStatus: MissionStatus): MissionStatusUI {
            return values().find { it.missionStatus == missionStatus }
                ?: throw IllegalArgumentException("Invalid Mission Status: $missionStatus")
        }
    }
}