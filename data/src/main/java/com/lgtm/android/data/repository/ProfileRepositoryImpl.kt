package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.ProfileDataSource
import com.lgtm.domain.entity.response.ProfileVO
import com.lgtm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileRepositoryImpl @Inject constructor(
    private val profileDataSource: ProfileDataSource
) : ProfileRepository {
    override suspend fun getProfileInfo(): Result<ProfileVO> {
        return try {
            val response = profileDataSource.getProfile()
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}