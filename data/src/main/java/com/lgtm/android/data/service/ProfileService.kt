package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.ProfileDTO
import retrofit2.Response
import retrofit2.http.GET

interface ProfileService {
    @GET("v1/member/profile")
    suspend fun getProfile(): Response<BaseDTO<ProfileDTO>>
}