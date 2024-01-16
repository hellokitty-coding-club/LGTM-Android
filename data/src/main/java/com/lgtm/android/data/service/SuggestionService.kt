package com.lgtm.android.data.service

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.MissionSuggestionDTO
import retrofit2.Response
import retrofit2.http.GET

interface SuggestionService {
    @GET("v1/suggestion")
    suspend fun getSuggestion(): Response<BaseDTO<MissionSuggestionDTO>>
}