package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.IntroResponse
import com.lgtm.android.data.service.IntroService
import javax.inject.Inject

class IntroDataSource @Inject constructor(
    private val introService: IntroService
) : BaseNetworkDataSource() {

    suspend fun getIntro(): BaseDTO<IntroResponse> {
        return checkResponse(introService.getIntro())
    }

}