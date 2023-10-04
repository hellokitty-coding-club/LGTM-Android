package com.lgtm.domain.usecase

import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.ProfileVO
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileDetailText
import com.lgtm.domain.profile.ProfileGlance
import com.lgtm.domain.profile.ProfileImage
import com.lgtm.domain.profile.ProfileTitleText
import com.lgtm.domain.profile.ProfileTitleTextType
import com.lgtm.domain.profile.TechTagList
import com.lgtm.domain.profile.ThickDivider
import com.lgtm.domain.profile.ThinDivider
import com.lgtm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {
    suspend fun fetchProfileInfo(userId: Int? = null): Result<List<Profile>> {
        return try {
            val response = profileRepository.getProfileInfo(userId).getOrNull()
                ?: return Result.failure(Exception("response is null"))

            val profileList = when (response.memberType) {
                Role.REVIEWEE -> getRevieweeProfile(response)
                Role.REVIEWER -> getReviewerProfile(response)
                else -> throw IllegalStateException("memberType is null")
            }
            Result.success(profileList)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun commonProfileList(response: ProfileVO) = listOf(
        ProfileImage(response.profileImageUrl),
        ProfileGlance(
            memberId = response.memberId,
            profileImage = response.profileImageUrl,
            nickname = response.nickname,
            githubId = response.githubId,
            educationalHistory = response.educationalHistory,
            position = response.position,
            company = response.company,
            careerPeriod = response.careerPeriod,
            memberType = requireNotNull(response.memberType)
        ),
        ThickDivider(),
        ProfileTitleText(ProfileTitleTextType.STACK),
        TechTagList(response.techTagList),
        ThinDivider(),
        ProfileTitleText(ProfileTitleTextType.INTRODUCTION),
        ProfileDetailText(response.introduction),
        ThinDivider()
    )

    private fun getRevieweeProfile(response: ProfileVO): List<Profile> {
        return commonProfileList(response) + listOf(
            ProfileTitleText(ProfileTitleTextType.PARTICIPATED_MISSION),
            *response.memberMissionHistory?.toTypedArray() ?: arrayOf()
        )
    }

    private fun getReviewerProfile(response: ProfileVO): List<Profile> {
        return commonProfileList(response) + listOf(
            ProfileTitleText(ProfileTitleTextType.CAREER),
            ProfileDetailText(response.careerPeriod.toString()),
            ThinDivider(),
            ProfileTitleText(ProfileTitleTextType.CONDUCTED_MISSION),
            *response.memberMissionHistory?.toTypedArray() ?: arrayOf()
        )
    }
}
