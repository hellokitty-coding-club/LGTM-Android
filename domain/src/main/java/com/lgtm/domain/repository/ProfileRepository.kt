package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.ProfileVO

interface ProfileRepository {
    suspend fun getProfileInfo(): Result<ProfileVO>
}