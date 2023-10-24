package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.SduiDTO
import com.lgtm.android.data.service.MissionService
import javax.inject.Inject

class SduiDataSource @Inject constructor(
    private val missionService: MissionService
) : BaseNetworkDataSource() {

    suspend fun getHomeMission(): BaseDTO<SduiDTO> {
        return checkResponse(missionService.getHomeMission())
    }
}