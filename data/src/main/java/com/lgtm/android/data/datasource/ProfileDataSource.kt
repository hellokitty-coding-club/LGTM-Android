package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.android.data.model.response.ProfileDTO
import com.lgtm.android.data.service.ProfileService
import javax.inject.Inject

class ProfileDataSource @Inject constructor(
    private val profileService: ProfileService
) : BaseNetworkDataSource() {

    suspend fun getProfile(): BaseDTO<ProfileDTO> {
        return checkResponse(profileService.getProfile())
    }
}