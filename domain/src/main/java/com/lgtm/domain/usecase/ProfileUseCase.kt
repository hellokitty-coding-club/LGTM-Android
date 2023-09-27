package com.lgtm.domain.usecase

import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileDetailText
import com.lgtm.domain.profile.ProfileGlance
import com.lgtm.domain.profile.ProfileImage
import com.lgtm.domain.profile.ProfileTitleText
import com.lgtm.domain.profile.TechTagList
import com.lgtm.domain.profile.ThickDivider
import com.lgtm.domain.profile.ThinDivider
import com.lgtm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend fun fetchProfileInfo(
        userId: Int? = null
    ): Result<List<Profile>> {
        return try {
            val response = profileRepository.getProfileInfo(userId).getOrNull().apply {
                if (this == null) return Result.failure(Exception("response is null"))
            }

            val list = listOf(
                ProfileImage(
                    response?.profileImageUrl ?: ""
                ),
                ProfileGlance(
                    memberId = response?.memberId ?: 0,
                    profileImage = response?.profileImageUrl ?: "",
                    nickname = response?.nickname ?: "",
                    githubId = response?.githubId ?: "",
                    educationalHistory = response?.educationalHistory,
                    position = response?.position,
                    company = response?.company,
                    careerPeriod = response?.careerPeriod,
                ),
                ThickDivider(),
                ProfileTitleText("사용 스택"),
                TechTagList(response?.techTagList ?: listOf()),
                ThinDivider(),
                ProfileTitleText("한 줄 소개"),
                ProfileDetailText(response?.introduction ?: ""),
                ThinDivider(),
                ProfileTitleText("참여한 미션"), // todo 한글 -> Presentation 로직으로
                *response?.memberMissionHistory?.toTypedArray() ?: arrayOf()
            )
            return Result.success(list)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}


