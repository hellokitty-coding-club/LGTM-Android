package com.lgtm.android.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.android.data.datasource.MissionDataSource
import com.lgtm.android.data.datasource.SduiDataSource
import com.lgtm.android.data.model.response.toVO
import com.lgtm.domain.constants.Role
import com.lgtm.domain.entity.request.PostMissionRequestDTO
import com.lgtm.domain.entity.response.DashboardVO
import com.lgtm.domain.entity.response.MissionDetailVO
import com.lgtm.domain.entity.response.PostMissionResponseVO
import com.lgtm.domain.entity.response.SduiVO
import com.lgtm.domain.repository.MissionRepository
import javax.inject.Inject

class MissionRepositoryImpl @Inject constructor(
    private val lgtmPreferenceDataSource: LgtmPreferenceDataSource,
    private val sduiDataSource: SduiDataSource,
    private val missionDataSource: MissionDataSource
) : MissionRepository {
    override suspend fun getHomeMission(): Result<SduiVO> {
        return try {
            val response = sduiDataSource.getHomeMission()
            Result.success(response.data.toVO())
        } catch (e: Exception) {
            Log.e(TAG, "getHomeMission: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun createMission(postMissionRequestDTO: PostMissionRequestDTO): Result<PostMissionResponseVO> {
        return try {
            val response = missionDataSource.createMission(postMissionRequestDTO)
            Result.success(response.data.toVO())
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "createMission: ${this.javaClass} ${e.message} / casting 도중 null 값 발생")
            Result.failure(e)
        } catch (e: Exception) {
            Log.e(TAG, "createMission: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun getMissionDetail(missionId: Int): Result<MissionDetailVO> {
        return try {
            val response = missionDataSource.getMissionDetail(missionId)
            val role = getMemberType()
            Result.success(response.data.toVO(role))
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "getMissionDetail: ${this.javaClass} ${e.message} / casting 도중 null 값 발생")
            Result.failure(e)
        } catch (e: Exception) {
            Log.e(TAG, "getMissionDetail: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

    override fun getMemberType(): Role {
        val role = lgtmPreferenceDataSource.getValue(
            preferenceKey = LgtmPreferenceDataSource.Companion.PreferenceKey.MEMBER_TYPE,
            defaultValue = "",
            isEncrypted = false
        )
        return Role.getRole(role) ?: throw IllegalArgumentException("role is not set")
    }

    override suspend fun fetchDashboardInfo(missionId: Int): Result<DashboardVO> {
        return try {
            val response = missionDataSource.fetchDashboardInfo(missionId)
            Result.success(response.data.toVO())
        } catch (e: IllegalArgumentException) {
            Log.e(TAG, "fetchDashboardInfo: ${this.javaClass} ${e.message} / casting 도중 null 값 발생")
            Result.failure(e)
        } catch (e: Exception) {
            Log.e(TAG, "fetchDashboardInfo: ${this.javaClass} ${e.message}")
            Result.failure(e)
        }
    }

}
