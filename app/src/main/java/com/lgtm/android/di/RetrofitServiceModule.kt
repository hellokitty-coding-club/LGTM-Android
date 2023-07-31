package com.lgtm.android.di

import com.lgtm.android.data.service.IntroService
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

}