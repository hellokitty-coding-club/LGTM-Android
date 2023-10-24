package com.lgtm.domain.repository

import com.lgtm.domain.entity.response.IntroVO

interface IntroRepository {

    suspend fun getIntro(): Result<IntroVO>
}