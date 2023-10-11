package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.DashboardDTO
import com.lgtm.android.data.model.response.MissionDetailDTO
import com.lgtm.android.data.model.response.PingPongJuniorDTO
import com.lgtm.android.data.model.response.PingPongSeniorDTO
import com.lgtm.android.data.model.response.PostMissionResponseDTO
import com.lgtm.android.data.model.response.SduiDTO
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import com.lgtm.domain.entity.response.JuniorPaymentConfirmDTO
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

    @POST("/v1/mission/{missionId}")
    suspend fun participateMission(
        @Path("missionId") missionId: Int
    ): Response<BaseDTO<Boolean>>

    @GET("/v1/mission/{missionId}/junior")
    suspend fun fetchJuniorMissionStatus(
        @Path("missionId") missionId: Int
    ): Response<BaseDTO<PingPongJuniorDTO>>

    @POST("/v1/mission/{missionId}/payment") //주니어 입금 확인 요쳥
    suspend fun confirmJuniorPayment(
        @Path("missionId") missionId: Int
    ): Response<BaseDTO<JuniorPaymentConfirmDTO>>


    /** 시니어 미션 참가자 별 상세 조회 */
    @GET("/v1/mission/{missionId}/senior/{juniorId}")
    suspend fun fetchSeniorMissionDetail(
        @Path("missionId") missionId: Int,
        @Path("juniorId") juniorId: Int
    ): Response<BaseDTO<PingPongSeniorDTO>>

}

