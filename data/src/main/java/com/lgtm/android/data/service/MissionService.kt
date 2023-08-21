package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.PostMissionResponseDTO
import com.lgtm.android.data.model.response.SduiDTO
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MissionService {

    @GET("v1/home")
    suspend fun getHomeMission(): Response<BaseDTO<SduiDTO>>

    @POST("v1/mission")
    suspend fun postMission(
        @Body postMissionRequest: PostMissionRequestDTO
    ): Response<BaseDTO<PostMissionResponseDTO>>

}

