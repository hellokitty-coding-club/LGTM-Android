package com.lgtm.android.di

import com.lgtm.android.data.repository.AuthRepositoryImpl
import com.lgtm.android.data.repository.IntroRepositoryImpl
import com.lgtm.android.data.repository.MissionRepositoryImpl
import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.repository.IntroRepository
import com.lgtm.domain.repository.MissionRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindsIntroRepository(
        introRepositoryImpl: IntroRepositoryImpl
    ): IntroRepository

    @Binds
    fun bindsAuthRepository(
        authRepositoryImpl: AuthRepositoryImpl
    ): AuthRepository

    @Binds
    fun bindsMissionRepository(
        missionRepositoryImpl: MissionRepositoryImpl
    ): MissionRepository

}