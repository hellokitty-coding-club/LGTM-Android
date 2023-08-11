package com.lgtm.android.di

import com.lgtm.android.data.service.AuthService
import com.lgtm.android.data.service.IntroService
import com.lgtm.android.data.service.MissionService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RetrofitServiceModule {

    @Provides
    @Singleton
    fun providesIntroService(retrofit: Retrofit): IntroService =
        retrofit.create(IntroService::class.java)

    @Provides
    @Singleton
    fun providesAuthService(retrofit: Retrofit): AuthService =
        retrofit.create(AuthService::class.java)

    @Provides
    @Singleton
    fun providesMissionService(retrofit: Retrofit): MissionService =
        retrofit.create(MissionService::class.java)
}