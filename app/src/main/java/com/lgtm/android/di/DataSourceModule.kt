package com.lgtm.android.di

import android.content.SharedPreferences
import com.lgtm.android.data.datasource.IntroDataSource
import com.lgtm.android.data.datasource.LgtmPreferenceDataSource
import com.lgtm.android.data.service.IntroService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    @Provides
    @Singleton
    fun provideIntroDataSource(introService: IntroService): IntroDataSource {
        return IntroDataSource(introService)
    }

    @Provides
    @Singleton
    fun provideLgtmPreferenceDataSource(
        @DefaultPreference sharedPreferences: SharedPreferences,
        @EncryptedPreference encryptedSharedPreferences: SharedPreferences
    ): LgtmPreferenceDataSource {
        return LgtmPreferenceDataSource(sharedPreferences, encryptedSharedPreferences)
    }

}
