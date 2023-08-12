package com.lgtm.android.common_ui.viewholder

import com.lgtm.android.common_ui.R
import com.lgtm.android.common_ui.databinding.ItemSduiEmptyBinding
import com.lgtm.domain.constants.Role
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiEmptyUiState
import com.lgtm.domain.server_drive_ui.SduiTheme
import com.lgtm.domain.server_drive_ui.SectionEmptyVO
import javax.inject.Inject

class SduiEmptyViewHolder @Inject constructor(
    private val binding: ItemSduiEmptyBinding,
) : SduiBaseHolder(binding) {

    override fun bind(theme: SduiTheme, viewContent: SduiContent, role: Role?) {
        check(viewContent is SectionEmptyVO) { "viewContent must be SectionEmptyVO" }
        val uiState: SduiEmptyUiState = when (viewContent.emptyViewTypeName) {
            ONGOING_MISSION_EMPTY_VIEW -> onOngoingMissionEmpty(requireNotNull(role))
            RECOMMENDED_MISSION_EMPTY_VIEW -> onRecommendedMissionEmpty()
            TOTAL_MISSION_EMPTY_VIEW -> onTotalMissionEmpty()
            else -> throw IllegalArgumentException("Invalid viewType")
        }

        binding.theme = theme
        binding.uiState = uiState
    }

    private fun onOngoingMissionEmpty(role: Role): SduiEmptyUiState {
        val subMessage =
            if (role == Role.REVIEWER) SUGGEST_ADDING_MISSION else SUGGEST_JOINING_MISSION
        val arrowImage =
            if (role == Role.REVIEWER) R.drawable.ic_home_arrow_right else R.drawable.ic_home_arrow_mid
        return SduiEmptyUiState(
            mainMessage = ONGOING_MISSION_MAIN_MESSAGE,
            subMessage = subMessage,
            isArrowVisible = true,
            arrowImage = arrowImage
        )
    }

    private fun onRecommendedMissionEmpty(): SduiEmptyUiState {
        return SduiEmptyUiState(
            mainMessage = RECOMMENDED_MISSION_MAIN_MESSAGE,
            subMessage = RECOMMENDED_MISSION_SUB_MESSAGE,
        )
    }

    private fun onTotalMissionEmpty(): SduiEmptyUiState {
        return SduiEmptyUiState(TOTAL_MISSION_MAIN_MESSAGE, TOTAL_MISSION_SUB_MESSAGE)
    }

    companion object {
        private const val ONGOING_MISSION_EMPTY_VIEW = "ongoing_mission_empty_view"
        private const val RECOMMENDED_MISSION_EMPTY_VIEW = "recommended_mission_empty_view"
        private const val TOTAL_MISSION_EMPTY_VIEW = "total_mission_empty_view"

        private const val SUGGEST_ADDING_MISSION = "아래에서 미션을 추가해보세요!"
        private const val SUGGEST_JOINING_MISSION = "아래에서 다양한 미션에 참여해보세요!"
        private const val RECOMMENDED_MISSION_MAIN_MESSAGE = "아직 추천 미션이 없습니다."
        private const val RECOMMENDED_MISSION_SUB_MESSAGE = "맞춤 미션을 준비 중입니다."
        private const val ONGOING_MISSION_MAIN_MESSAGE = "진행중인 미션이 없습니다."
        private const val TOTAL_MISSION_MAIN_MESSAGE = "아직 미션이 없습니다."
        private const val TOTAL_MISSION_SUB_MESSAGE = "다양한 미션을 준비 중입니다."

    }
}
