package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.MemberData

interface AuthRepository {
    fun saveUserData(memberData: MemberData)
    fun saveAccessToken(accessToken: String)
    fun saveRefreshToken(refreshToken: String)
    fun saveMemberType(memberType: String)
    fun isAutoLoginAvailable(): Boolean

}