package com.lgtm.android.common_ui.constant

import androidx.annotation.StringRes
import com.lgtm.android.common_ui.R
import com.lgtm.domain.constants.MissionDetailStatus


enum class MissionDetailButtonStatus(@StringRes val string: Int, val isEnable: Boolean) {
    JUNIOR_PARTICIPATE_RECRUITING(R.string.mission_progress_management, true),
    JUNIOR_PARTICIPATE_MISSION_FINISH(R.string.mission_progress_management, true),
    JUNIOR_NOT_PARTICIPATE_RECRUITING(R.string.mission_participate, true),
    JUNIOR_NOT_PARTICIPATE_MISSION_FINISH(R.string.mission_recruiting_finished, false),
    SENIOR_PARTICIPATE_RECRUITING(R.string.mission_progress_management, true),
    SENIOR_PARTICIPATE_MISSION_FINISH(R.string.mission_progress_management, true),
    SENIOR_NOT_PARTICIPATE_RECRUITING(R.string.reviewer_cannot_participate, false),
    SENIOR_NOT_PARTICIPATE_MISSION_FINISH(R.string.reviewer_cannot_participate, false);

    companion object {
        fun getButtonStatusUI(
            missionDetailButtonStatus: MissionDetailStatus
        ): MissionDetailButtonStatus {
            values().forEach {
                if (it.name == missionDetailButtonStatus.name) return it
            }
            throw IllegalStateException("ButtonStatusUI not found")
        }
    }
}