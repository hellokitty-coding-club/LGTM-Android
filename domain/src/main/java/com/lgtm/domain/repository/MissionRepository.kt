package com.lgtm.domain.repository

import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.entity.response.PostMissionResponseVO
import com.lgtm.domain.entity.response.SduiVO

interface MissionRepository {
    fun getMemberType(): Role
    suspend fun getHomeMission(): Result<SduiVO>
    suspend fun createMission(postMissionRequestDTO: PostMissionRequestDTO): Result<PostMissionResponseVO>
    suspend fun getMissionDetail(missionId: Int): Result<MissionDetailVO>
    suspend fun fetchDashboardInfo(missionId: Int): Result<DashboardVO>
}