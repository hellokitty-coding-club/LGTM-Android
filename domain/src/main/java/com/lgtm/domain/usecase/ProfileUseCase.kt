package com.lgtm.domain.usecase

import com.lgtm.domain.entity.response.ProfileVO
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
    authRepository: AuthRepository
) {

    private val role = authRepository.getMemberType()

    suspend fun getProfileInfo(): Result<ProfileVO> {
        return try {
            return profileRepository.getProfileInfo()
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}

