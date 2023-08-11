package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.SduiDTO
import retrofit2.Response
import retrofit2.http.GET

interface MissionService {

    @GET("v1/home")
    suspend fun getHomeMission(): Response<BaseDTO<SduiDTO>>

}

