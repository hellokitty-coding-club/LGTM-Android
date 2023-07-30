package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.IntroResponse
import retrofit2.Response
import retrofit2.http.GET

interface IntroService {

    @GET("v1/intro")
    suspend fun getIntro(): Response<BaseDTO<IntroResponse>>
}