package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.MemberDataDTO

interface AuthRepository {
    fun saveUserData(memberData: MemberDataDTO)
    fun saveAccessToken(accessToken: String)
    fun saveRefreshToken(refreshToken: String)
    fun saveMemberType(memberType: String)
    fun isAutoLoginAvailable(): Boolean

}