package com.lgtm.android.data.repository

import com.lgtm.android.data.remote.datasource.IntroDataSourceImpl
import com.lgtm.android.data.remote.model.response.BaseResponse
import com.lgtm.android.data.remote.model.response.IntroDTO
import com.lgtm.domain.entity.response.IntroVO
import com.lgtm.domain.repository.IntroRepository
import javax.inject.Inject

class IntroRepositoryImpl @Inject constructor(
    private val introDataSourceImpl: IntroDataSourceImpl
) : IntroRepository {
    override suspend fun getIntro(): Result<IntroVO> {
        val response: BaseResponse<IntroDTO> = introDataSourceImpl.getIntro()
        if (response.success)
            return Result.success(response.data.toVO())
        return Result.failure(Exception(response.message))
    }
}