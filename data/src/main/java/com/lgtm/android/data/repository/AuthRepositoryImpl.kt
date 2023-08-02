package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.AuthDataSource
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.android.data.model.request.SignUpJuniorRequestDTO
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.SignUpJuniorRequestVO
import com.lgtm.domain.entity.request.SignUpSeniorRequestVO
import com.lgtm.domain.entity.response.MemberDataDTO
import com.lgtm.domain.entity.response.SignUpResponseVO
import com.lgtm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val lgtmPreferenceDataSource: LgtmPreferenceDataSource,
    private val authDataSource: AuthDataSource
) : AuthRepository {

    override fun saveUserData(memberData: MemberDataDTO) {
        saveAccessToken(requireNotNull(memberData.accessToken))
        saveRefreshToken(requireNotNull(memberData.refreshToken))
        saveMemberType(requireNotNull(memberData.memberType))
    }

    override fun saveAccessToken(accessToken: String) {
        lgtmPreferenceDataSource.setAccessToken(accessToken)
    }

    override fun saveRefreshToken(refreshToken: String) {
        lgtmPreferenceDataSource.setRefreshToken(refreshToken)
    }

    override fun saveMemberType(memberType: String) {
        check(Role.isProperRole(memberType)) {
            "memberType must be SENIOR or JUNIOR"
        }
        lgtmPreferenceDataSource.setMemberType(memberType)
    }

    override fun isAutoLoginAvailable(): Boolean {
        return lgtmPreferenceDataSource.isAutoLogin()
    }

    override suspend fun signUpJunior(signUpJuniorVO: SignUpJuniorRequestVO): Result<SignUpResponseVO> {
        val response = authDataSource.signUpJunior(
            SignUpJuniorRequestDTO(
                githubId = signUpJuniorVO.githubId,
                githubOauthId = signUpJuniorVO.githubOauthId,
                nickName = signUpJuniorVO.nickName,
                deviceToken = signUpJuniorVO.deviceToken ?: null,
                profileImageUrl = signUpJuniorVO.profileImageUrl,
                introduction = signUpJuniorVO.introduction,
                tagList = signUpJuniorVO.tagList,
                educationalHistory = signUpJuniorVO.educationalHistory,
                realName = signUpJuniorVO.realName,
                agreeWithEventInfo = signUpJuniorVO.isAgreeWithEventInfo
            )
        )
        if (response.success)
            return Result.success(response.data.toVO())
        return Result.failure(Exception(response.message))
    }

    override suspend fun signUpSenior(signUpSeniorVO: SignUpSeniorRequestVO): Result<SignUpResponseVO> {
        TODO("Not yet implemented")
    }


}