package com.lgtm.android.di

import com.lgtm.android.data.remote.datasource.IntroDataSourceImpl
import com.lgtm.android.data.remote.service.IntroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RemoteDataSourceModule {

    @Provides
    @Singleton
    fun provideIntroDataSource(introService: IntroService): IntroDataSourceImpl {
        return IntroDataSourceImpl(introService)
    }
}
