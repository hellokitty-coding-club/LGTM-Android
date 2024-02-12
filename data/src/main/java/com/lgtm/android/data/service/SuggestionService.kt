package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.CreateSuggestionResponseDTO
import com.lgtm.android.data.model.response.MissionSuggestionDTO
import com.lgtm.android.data.model.response.SuggestionDTO
import com.lgtm.domain.entity.request.CreateSuggestionRequestDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface SuggestionService {
    @GET("v1/suggestion")
    suspend fun getSuggestion(): Response<BaseDTO<MissionSuggestionDTO>>

    @GET("v1/suggestion/{suggestionId}")
    suspend fun getSuggestionDetail(
        @Path("suggestionId") suggestionId: Int
    ): Response<BaseDTO<SuggestionDTO>>

    @POST("v1/suggestion")
    suspend fun createSuggestion(
        @Body createSuggestionRequestDTO: CreateSuggestionRequestDTO
    ): Response<BaseDTO<CreateSuggestionResponseDTO>>
}