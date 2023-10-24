package com.lgtm.android.data.repository

import com.lgtm.android.data.datasource.IntroDataSource
import com.lgtm.domain.entity.response.IntroVO
import com.lgtm.domain.repository.IntroRepository
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
    private val introDataSource: IntroDataSource
) : IntroRepository {
    override suspend fun getIntro(): Result<IntroVO> {
        try {
            val response = introDataSource.getIntro()
            if (response.success)
                return Result.success(response.data.toVO())
            return Result.failure(Exception(response.message))
        } catch (e: Exception) {
            return Result.failure(e)
        }
    }
}