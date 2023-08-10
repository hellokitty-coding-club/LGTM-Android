package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.SduiVO

interface MissionRepository {
    suspend fun getHomeMission(): Result<SduiVO>
}