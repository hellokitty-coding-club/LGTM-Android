package com.lgtm.android.data.remote.datasource

import com.lgtm.android.data.remote.model.response.BaseResponse
import com.lgtm.android.data.remote.model.response.IntroDTO
import com.lgtm.android.data.remote.service.IntroService
import javax.inject.Inject

class IntroDataSource @Inject constructor(
    private val introService: IntroService
) : BaseNetworkDataSource() {

    suspend fun getIntro(): BaseResponse<IntroDTO> {
        return checkResponse(introService.getIntro())
    }

}