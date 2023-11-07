package com.lgtm.domain.repository

import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.SignUpJuniorRequestVO
import com.lgtm.domain.entity.request.SignUpSeniorRequestVO
import com.lgtm.domain.entity.response.MemberDataDTO
import com.lgtm.domain.entity.response.SignUpResponseVO

interface AuthRepository {
    fun saveUserData(memberData: MemberDataDTO)
    fun saveUserData(signUpResponseVO: SignUpResponseVO)

    fun clearUserData()
    fun saveAccessToken(accessToken: String)
    fun saveRefreshToken(refreshToken: String)
    fun saveMemberType(memberType: String)
    fun saveMemberId(memberId: Int)
    fun getMemberId(): Int?
    fun getMemberType(): Role
    fun isAutoLoginAvailable(): Boolean
    suspend fun signUpJunior(signUpJuniorVO: SignUpJuniorRequestVO): Result<SignUpResponseVO>
    suspend fun signUpSenior(signUpSeniorVO: SignUpSeniorRequestVO): Result<SignUpResponseVO>
    fun getDeviceToken(tokenCallBack: (String?) -> Unit)
    suspend fun patchDeviceToken(token: String?): Result<Boolean>

}