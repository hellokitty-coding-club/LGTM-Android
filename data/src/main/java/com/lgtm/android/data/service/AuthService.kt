package com.lgtm.android.data.service

import com.lgtm.android.data.model.request.SignUpJuniorRequestDTO
import com.lgtm.android.data.model.request.SignUpSeniorRequestDTO
import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.SignUpResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface AuthService {
    @POST("v1/signup/junior")
    suspend fun signUpJunior(@Body signUpJuniorRequest: SignUpJuniorRequestDTO): Response<BaseDTO<SignUpResponse>>

    @POST("v1/signup/senior")
    suspend fun signUpSenior(@Body signUpSeniorRequest: SignUpSeniorRequestDTO): Response<BaseDTO<SignUpResponse>>

    @PATCH("v1/member/device-token")
    suspend fun patchDeviceToken(@Query("deviceToken") deviceToken: String?): Response<BaseDTO<Boolean>>
}