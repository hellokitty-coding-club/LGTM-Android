package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.MissionSuggestionDTO
import com.lgtm.android.data.model.response.SuggestionDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuggestionService {
    @GET("v1/suggestion")
    suspend fun getSuggestion(): Response<BaseDTO<MissionSuggestionDTO>>

    @GET("v1/suggestion/{suggestionId}")
    suspend fun getSuggestionDetail(
        @Path("suggestionId") suggestionId: Int
    ): Response<BaseDTO<SuggestionDTO>>
}