package com.lgtm.android.data.service

import com.lgtm.android.data.model.request.SignUpJuniorRequestDTO
import com.lgtm.android.data.model.request.SignUpSeniorRequestDTO
import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("v1/signup/junior")
    suspend fun signUpJunior(@Body signUpJuniorRequest: SignUpJuniorRequestDTO): Response<BaseDTO<SignUpResponse>>

    @POST("v1/signup/senior")
    suspend fun signUpSenior(@Body signUpSeniorRequest: SignUpSeniorRequestDTO): Response<BaseDTO<SignUpResponse>>

}