package com.lgtm.domain.usecase

import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.ProfileVO
import com.lgtm.domain.profile.Profile
import com.lgtm.domain.profile.ProfileTitleTextType
import com.lgtm.domain.profile.profileViewType.ProfileDetailText
import com.lgtm.domain.profile.profileViewType.ProfileGlance
import com.lgtm.domain.profile.profileViewType.ProfileImage
import com.lgtm.domain.profile.profileViewType.ProfileTitleText
import com.lgtm.domain.profile.profileViewType.TechTagList
import com.lgtm.domain.profile.profileViewType.ThickDivider
import com.lgtm.domain.profile.profileViewType.ThinDivider
import com.lgtm.domain.repository.ProfileRepository
import javax.inject.Inject

class ProfileUseCase @Inject constructor(
    private val profileRepository: ProfileRepository,
) {

    private lateinit var role: Role

    suspend fun fetchProfileInfo(userId: Int? = null): Result<List<Profile>> {
        return try {
            val response = profileRepository.getProfileInfo(userId).getOrNull()
                ?: return Result.failure(Exception("response is null"))

            role = response.memberType ?: throw IllegalStateException("memberType is null")

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
        ProfileImage(response.profileImageUrl, response.isMyProfile),
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
            ProfileDetailText(response.careerPeriod.toString(), isCareer = true),
            ThinDivider(),
            ProfileTitleText(ProfileTitleTextType.CONDUCTED_MISSION),
            *response.memberMissionHistory?.toTypedArray() ?: arrayOf()
        )
    }

    fun getFirstMissionIdx(): Int {
        return when (role) {
            Role.REVIEWEE -> 10
            Role.REVIEWER -> 13
        }
    }
}
