package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.request.SignUpJuniorRequestDTO
import com.lgtm.android.data.model.request.SignUpSeniorRequestDTO
import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.SignUpResponse
import com.lgtm.android.data.service.AuthService
import javax.inject.Inject

class AuthDataSource @Inject constructor(
    private val authService: AuthService
) : BaseNetworkDataSource() {

    suspend fun signUpJunior(signUpJuniorRequest: SignUpJuniorRequestDTO): BaseDTO<SignUpResponse> {
        return checkResponse(authService.signUpJunior(signUpJuniorRequest))
    }

    suspend fun signUpSenior(signUpSeniorRequest: SignUpSeniorRequestDTO): BaseDTO<SignUpResponse> {
        return checkResponse(authService.signUpSenior(signUpSeniorRequest))
    }
}