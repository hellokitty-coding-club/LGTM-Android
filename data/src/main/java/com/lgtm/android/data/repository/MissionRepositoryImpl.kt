package com.lgtm.android.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.lgtm.android.data.datasource.SduiDataSource
import com.lgtm.android.data.model.response.toVO
import com.lgtm.domain.entity.response.SduiVO
import com.lgtm.domain.repository.MissionRepository
import javax.inject.Inject

class MissionRepositoryImpl @Inject constructor(
    private val sduiDataSource: SduiDataSource
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
}