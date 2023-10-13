package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.request.GithubPrUrlRequest
import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.DashboardDTO
import com.lgtm.android.data.model.response.MissionDetailDTO
import com.lgtm.android.data.model.response.PingPongJuniorDTO
import com.lgtm.android.data.model.response.PingPongSeniorDTO
import com.lgtm.android.data.model.response.PostMissionResponseDTO
import com.lgtm.android.data.service.MissionService
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import com.lgtm.domain.entity.response.PingPongResponse
import javax.inject.Inject

class MissionDataSource @Inject constructor(
    private val missionService: MissionService
) : BaseNetworkDataSource() {
    suspend fun createMission(postMissionRequest: PostMissionRequestDTO): BaseDTO<PostMissionResponseDTO> {
        return checkResponse(missionService.postMission(postMissionRequest = postMissionRequest))
    }

    suspend fun getMissionDetail(missionId: Int): BaseDTO<MissionDetailDTO> {
        return checkResponse(missionService.getMissionDetail(missionId = missionId))
    }

    suspend fun fetchDashboardInfo(missionId: Int): BaseDTO<DashboardDTO> {
        return checkResponse(missionService.fetchDashboardInfo(missionId = missionId))
    }

    suspend fun participateMission(missionId: Int): BaseDTO<Boolean> {
        return checkResponse(missionService.participateMission(missionId = missionId))
    }

    suspend fun fetchJuniorMissionStatus(missionId: Int): BaseDTO<PingPongJuniorDTO> {
        return checkResponse(missionService.fetchJuniorMissionStatus(missionId = missionId))
    }

    suspend fun confirmJuniorPayment(missionId: Int): BaseDTO<PingPongResponse> {
        return checkResponse(missionService.confirmJuniorPayment(missionId = missionId))
    }

    suspend fun fetchSeniorMissionStatus(
        missionId: Int,
        juniorId: Int
    ): BaseDTO<PingPongSeniorDTO> {
        return checkResponse(
            missionService.fetchSeniorMissionDetail(
                missionId = missionId,
                juniorId = juniorId
            )
        )
    }

    suspend fun confirmDepositCompleted(missionId: Int, juniorId: Int): BaseDTO<PingPongResponse> {
        return checkResponse(
            missionService.confirmDepositCompleted(
                missionId = missionId,
                juniorId = juniorId
            )
        )
    }

    suspend fun submitPullRequest(missionId: Int, githubPrUrl: String): BaseDTO<PingPongResponse> {
        return checkResponse(
            missionService.submitPullRequest(
                missionId = missionId,
                githubPrUrl = GithubPrUrlRequest(githubPrUrl = githubPrUrl)
            )
        )
    }
}