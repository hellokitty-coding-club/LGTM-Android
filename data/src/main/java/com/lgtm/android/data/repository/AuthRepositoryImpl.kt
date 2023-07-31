package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.response.MemberDataDTO
import com.lgtm.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val lgtmPreferenceDataSource: LgtmPreferenceDataSource
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

}