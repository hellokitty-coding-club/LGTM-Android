package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.ProfileDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ProfileService {
    @GET("v1/member/profile")
    suspend fun getProfile(
        @Query("memberId") userId: Int? = null
    ): Response<BaseDTO<ProfileDTO>>
}