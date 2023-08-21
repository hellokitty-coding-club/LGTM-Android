package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.PostMissionResponseDTO
import com.lgtm.android.data.service.MissionService
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import javax.inject.Inject

class MissionDataSource @Inject constructor(
    private val missionService: MissionService
) : BaseNetworkDataSource() {
    suspend fun createMission(postMissionRequest: PostMissionRequestDTO): BaseDTO<PostMissionResponseDTO> {
        return checkResponse(missionService.postMission(postMissionRequest = postMissionRequest))
    }
}