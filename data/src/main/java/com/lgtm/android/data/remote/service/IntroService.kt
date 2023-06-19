package com.lgtm.android.data.remote.service

import com.lgtm.android.data.remote.model.response.BaseResponse
import com.lgtm.android.data.remote.model.response.IntroDTO
import retrofit2.Response
import retrofit2.http.GET

interface IntroService {

    @GET("api/v1/intro")
    suspend fun getIntro(): Response<BaseResponse<IntroDTO>>
}