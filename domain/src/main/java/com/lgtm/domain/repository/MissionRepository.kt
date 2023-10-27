package com.lgtm.domain.repository

import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.entity.response.PingPongJuniorVO
import com.lgtm.domain.entity.response.PingPongSeniorVO
import com.lgtm.domain.entity.response.PostMissionResponseVO
import com.lgtm.domain.entity.response.SduiVO

interface MissionRepository {
    fun getMemberType(): Role
    suspend fun getHomeMission(): Result<SduiVO>
    suspend fun createMission(postMissionRequestDTO: PostMissionRequestDTO): Result<PostMissionResponseVO>
    suspend fun getMissionDetail(missionId: Int): Result<MissionDetailVO>
    suspend fun fetchDashboardInfo(missionId: Int): Result<DashboardVO>
    suspend fun participateMission(missionId: Int): Result<Boolean>
    suspend fun fetchJuniorMissionStatus(missionId: Int): Result<PingPongJuniorVO>
    suspend fun confirmJuniorPayment(missionId: Int): Result<Boolean>

    suspend fun fetchSeniorMissionStatus(
        missionId: Int, juniorId: Int
    ): Result<PingPongSeniorVO>

    suspend fun confirmDepositCompleted(missionId: Int, juniorId: Int): Result<Boolean>

    suspend fun submitPullRequest(missionId: Int, githubPrUrl: String): Result<Boolean>
    suspend fun codeReviewCompleted(missionId: Int, juniorId: Int): Result<Boolean>
    suspend fun deleteMission(missionId: Int): Result<Boolean>
}