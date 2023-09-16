package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.DashboardDTO
import com.lgtm.android.data.model.response.MissionDetailDTO
import com.lgtm.android.data.model.response.PostMissionResponseDTO
import com.lgtm.android.data.model.response.SduiDTO
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MissionService {

    @GET("v1/home")
    suspend fun getHomeMission(): Response<BaseDTO<SduiDTO>>

    @POST("v1/mission")
    suspend fun postMission(
        @Body postMissionRequest: PostMissionRequestDTO
    ): Response<BaseDTO<PostMissionResponseDTO>>

    @GET("v1/mission/detail")
    suspend fun getMissionDetail(
        @Query("missionId") missionId: Int
    ): Response<BaseDTO<MissionDetailDTO>>

    @GET("/v1/mission/{missionId}/senior")
    suspend fun fetchDashboardInfo(
        @Path("missionId") missionId: Int
    ): Response<BaseDTO<DashboardDTO>>
}

