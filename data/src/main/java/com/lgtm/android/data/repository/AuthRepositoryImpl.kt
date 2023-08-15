package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.AuthDataSource
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource.Companion.PreferenceKey
import com.lgtm.android.data.model.request.DeviceTokenRequest
import com.lgtm.android.data.model.request.SignUpJuniorRequestDTO
import com.lgtm.android.data.model.request.SignUpSeniorRequestDTO
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.SignUpJuniorRequestVO
import com.lgtm.domain.entity.request.SignUpSeniorRequestVO
import com.lgtm.domain.entity.response.MemberDataDTO
import com.lgtm.domain.entity.response.SignUpResponseVO
import com.lgtm.domain.firebase.LgtmMessagingService
import com.lgtm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val lgtmPreferenceDataSource: LgtmPreferenceDataSource,
    private val authDataSource: AuthDataSource,
    private val lgtmFirebaseMessagingService: LgtmMessagingService
) : AuthRepository {

    override fun saveUserData(memberData: MemberDataDTO) {
        saveAccessToken(requireNotNull(memberData.accessToken))
        saveRefreshToken(requireNotNull(memberData.refreshToken))
        saveMemberType(requireNotNull(memberData.memberType))
    }

    override fun saveUserData(signUpResponseVO: SignUpResponseVO, memberType: String?) {
        saveAccessToken(requireNotNull(signUpResponseVO.accessToken))
        saveRefreshToken(requireNotNull(signUpResponseVO.refreshToken))
        saveMemberType(requireNotNull(memberType))  // todo 추후에 SignUpResponseVO에 memberType이 추가되면 변경
    }


    override fun saveAccessToken(accessToken: String) {
        lgtmPreferenceDataSource.setValue(
            preferenceKey = PreferenceKey.ACCESS_TOKEN,
            value = "Bearer $accessToken",
            isEncrypted = true,
            byAsync = false
        )
    }

    override fun saveRefreshToken(refreshToken: String) {
        lgtmPreferenceDataSource.setValue(
            preferenceKey = PreferenceKey.REFRESH_TOKEN,
            value = "Bearer $refreshToken",
            isEncrypted = true,
            byAsync = false
        )
    }

    override fun saveMemberType(memberType: String) {
        check(Role.isProperRole(memberType)) {
            "memberType must be SENIOR or JUNIOR"
        }
        lgtmPreferenceDataSource.setValue(
            preferenceKey = PreferenceKey.MEMBER_TYPE,
            value = memberType,
            isEncrypted = false,
            byAsync = true
        )
    }

    override fun getMemberType(): Role {
        val role = lgtmPreferenceDataSource.getValue(
            preferenceKey = PreferenceKey.MEMBER_TYPE,
            defaultValue = "",
            isEncrypted = false
        )
        return Role.getRole(role)
    }

    override fun isAutoLoginAvailable(): Boolean {
        val token = lgtmPreferenceDataSource.getValue(
            preferenceKey = PreferenceKey.ACCESS_TOKEN,
            defaultValue = "",
            isEncrypted = true
        )
        return token != ""
    }

    override suspend fun signUpJunior(signUpJuniorVO: SignUpJuniorRequestVO): Result<SignUpResponseVO> {
        try {
            val response = authDataSource.signUpJunior(
                SignUpJuniorRequestDTO(
                    githubId = signUpJuniorVO.githubId,
                    githubOauthId = signUpJuniorVO.githubOauthId,
                    nickName = signUpJuniorVO.nickName,
                    deviceToken = signUpJuniorVO.deviceToken,
                    profileImageUrl = signUpJuniorVO.profileImageUrl,
                    introduction = signUpJuniorVO.introduction,
                    tagList = signUpJuniorVO.tagList,
                    educationalHistory = signUpJuniorVO.educationalHistory,
                    realName = signUpJuniorVO.realName,
                    isAgreeWithEventInfo = signUpJuniorVO.isAgreeWithEventInfo
                )
            )
            return Result.success(response.data.toVO())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override suspend fun signUpSenior(signUpSeniorVO: SignUpSeniorRequestVO): Result<SignUpResponseVO> {
        try {
            val response = authDataSource.signUpSenior(
                SignUpSeniorRequestDTO(
                    githubId = signUpSeniorVO.githubId,
                    githubOauthId = signUpSeniorVO.githubOauthId,
                    nickName = signUpSeniorVO.nickName,
                    deviceToken = signUpSeniorVO.deviceToken,
                    profileImageUrl = signUpSeniorVO.profileImageUrl,
                    introduction = signUpSeniorVO.introduction,
                    tagList = signUpSeniorVO.tagList,
                    accountNumber = signUpSeniorVO.accountNumber,
                    bankName = signUpSeniorVO.bankName,
                    careerPeriod = signUpSeniorVO.careerPeriod,
                    companyInfo = signUpSeniorVO.companyInfo,
                    position = signUpSeniorVO.position,
                    isAgreeWithEventInfo = signUpSeniorVO.isAgreeWithEventInfo
                )
            )
            return Result.success(response.data.toVO())
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }

    override fun getDeviceToken(tokenCallBack: (String?) -> Unit) {
        lgtmFirebaseMessagingService.getDeviceToken(tokenCallBack)
    }

    override suspend fun patchDeviceToken(token: String?): Result<Boolean> {
        return try {
            val response = authDataSource.patchDeviceToken(DeviceTokenRequest(deviceToken = token))
            Result.success(response.data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}