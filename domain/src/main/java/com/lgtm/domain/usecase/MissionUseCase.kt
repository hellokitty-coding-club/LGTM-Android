package com.lgtm.domain.usecase

import com.lgtm.domain.constants.ArrowDirection
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.entity.response.MissionProcessInfoVO
import com.lgtm.domain.entity.response.PingPongJuniorVO
import com.lgtm.domain.entity.response.PingPongSeniorVO
import com.lgtm.domain.entity.response.SduiVO
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.MissionRepository
import com.lgtm.domain.server_drive_ui.SduiContent
import com.lgtm.domain.server_drive_ui.SduiEmptyUiState
import com.lgtm.domain.server_drive_ui.SduiViewType
import com.lgtm.domain.server_drive_ui.SectionEmptyVO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import org.jsoup.Jsoup
import javax.inject.Inject

class MissionUseCase @Inject constructor(
    private val missionRepository: MissionRepository,
    authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher,
) {

    private val role = authRepository.getMemberType()

    suspend fun getHomeMission(): Result<SduiVO> {
        return try {
            val response = missionRepository.getHomeMission().getOrNull()
            return Result.success(response?.copy(contents = response.contents.map {
                when (it.viewType) {
                    SduiViewType.EMPTY -> {
                        val emptyUiState: SduiContent =
                            getMissionEmptyUiState(it.content as SectionEmptyVO)
                        it.copy(content = emptyUiState)
                    }

                    else -> it
                }
            }) ?: throw IllegalStateException("${this.javaClass} response is null"))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun getMissionEmptyUiState(setEmptyVO: SectionEmptyVO): SduiEmptyUiState {
        return when (setEmptyVO.emptyViewTypeName) {
            ONGOING_MISSION_EMPTY_VIEW -> onOngoingMissionEmpty(role)
            RECOMMENDED_MISSION_EMPTY_VIEW -> onRecommendedMissionEmpty()
            TOTAL_MISSION_EMPTY_VIEW -> onTotalMissionEmpty()
            else -> throw IllegalArgumentException("Invalid viewType")
        }
    }

    private fun onOngoingMissionEmpty(role: Role): SduiEmptyUiState {
        val subMessage =
            if (role == Role.REVIEWER) SUGGEST_ADDING_MISSION else SUGGEST_JOINING_MISSION
        val arrowType =
            if (role == Role.REVIEWER) ArrowDirection.DOWNWARD_RIGHT else ArrowDirection.DOWNWARD
        return SduiEmptyUiState(
            mainMessage = ONGOING_MISSION_MAIN_MESSAGE,
            subMessage = subMessage,
            isArrowVisible = true,
            arrowType = arrowType
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

    suspend fun getMissionDetail(missionId: Int): Result<MissionDetailVO> {
        return try {
            missionRepository.getMissionDetail(missionId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchDashboardInfo(missionID: Int): Result<DashboardVO> {
        return try {
            val dashboardInfo = retrieveDashboardInfo(missionID)
            val processedInfo = processDashboardInfo(dashboardInfo)
            Result.success(processedInfo)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private suspend fun retrieveDashboardInfo(missionID: Int): DashboardVO {
        return missionRepository.fetchDashboardInfo(missionID).getOrNull()
            ?: throw java.lang.IllegalArgumentException("${this.javaClass} response is null")
    }

    private fun processDashboardInfo(dashboardInfo: DashboardVO): DashboardVO {
        return dashboardInfo.copy(
            memberInfoList = dashboardInfo.memberInfoList.map { memberInfo ->
                memberInfo.copy(
                    missionFinishedDate = memberInfo.missionFinishedDate,
                    paymentDate = memberInfo.paymentDate
                )
            }
        )
    }

    suspend fun participateMission(missionID: Int): Result<Boolean> {
        return try {
            missionRepository.participateMission(missionID)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchJuniorMissionStatus(missionID: Int): Result<PingPongJuniorVO> {
        return try {
            val response = missionRepository.fetchJuniorMissionStatus(missionID)
                .getOrElse { throw NoSuchElementException("Response is null") }
            val formattedTimeStamp = formattingTimestamps(response.missionProcessInfo)
            Result.success(response.copy(missionProcessInfo = formattedTimeStamp))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun confirmJuniorPayment(missionID: Int): Result<Boolean> {
        return try {
            missionRepository.confirmJuniorPayment(missionID)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun fetchSeniorMissionStatus(missionId: Int, juniorId: Int): Result<PingPongSeniorVO> {
        return try {
            val response = missionRepository.fetchSeniorMissionStatus(
                missionId = missionId,
                juniorId = juniorId
            ).getOrElse { throw NoSuchElementException("Response is null") }
            val formattedTimeStamp = formattingTimestamps(response.missionProcessInfo)
            Result.success(response.copy(missionProcessInfo = formattedTimeStamp))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun formattingTimestamps(missionProcessInfo: MissionProcessInfoVO): MissionProcessInfoVO {
        return missionProcessInfo.copy(
            waitingForPaymentDate = missionProcessInfo.waitingForPaymentDate,
            paymentConfirmationDate = missionProcessInfo.paymentConfirmationDate,
            missionProceedingDate = missionProcessInfo.missionProceedingDate,
            codeReviewDate = missionProcessInfo.codeReviewDate,
            missionFinishedDate = missionProcessInfo.missionFinishedDate,
            feedbackReviewedDate = missionProcessInfo.feedbackReviewedDate
        )
    }

    suspend fun confirmDepositCompleted(missionId: Int, juniorId: Int): Result<Boolean> {
        return try {
            missionRepository.confirmDepositCompleted(missionId, juniorId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun submitPullRequest(missionId: Int, githubPrUrl: String): Result<Boolean> {
        return try {
            missionRepository.submitPullRequest(missionId, githubPrUrl)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getOgImage(pullRequestUrl: String): Result<String> {
        return try {
            withContext(dispatcher) {
                val document = Jsoup.connect(pullRequestUrl).get()
                val ogImage = document.select("meta[property=og:image]").attr("content")
                Result.success(ogImage)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun codeReviewCompleted(missionId: Int, juniorId: Int): Result<Boolean> {
        return try {
            missionRepository.codeReviewCompleted(missionId, juniorId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteMission(missionId: Int): Result<Boolean> {
        return try {
            missionRepository.deleteMission(missionId)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    companion object {
        // viewType
        private const val ONGOING_MISSION_EMPTY_VIEW = "ongoing_mission_empty_view"
        private const val RECOMMENDED_MISSION_EMPTY_VIEW = "recommended_mission_empty_view"
        private const val TOTAL_MISSION_EMPTY_VIEW = "total_mission_empty_view"

        // message
        private const val SUGGEST_ADDING_MISSION = "아래에서 미션을 추가해보세요!"
        private const val SUGGEST_JOINING_MISSION = "아래에서 다양한 미션에 참여해보세요!"
        private const val RECOMMENDED_MISSION_MAIN_MESSAGE = "아직 추천 미션이 없습니다."
        private const val RECOMMENDED_MISSION_SUB_MESSAGE = "맞춤 미션을 준비 중입니다."
        private const val ONGOING_MISSION_MAIN_MESSAGE = "진행중인 미션이 없습니다."
        private const val TOTAL_MISSION_MAIN_MESSAGE = "아직 미션이 없습니다."
        private const val TOTAL_MISSION_SUB_MESSAGE = "다양한 미션을 준비 중입니다."
    }
}
