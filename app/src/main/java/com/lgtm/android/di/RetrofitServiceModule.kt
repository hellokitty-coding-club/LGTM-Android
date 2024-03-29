package com.lgtm.android.di

import com.lgtm.android.data.service.AuthService
import com.lgtm.android.data.service.IntroService
import com.lgtm.android.data.service.MissionService
import com.lgtm.android.data.service.NotificationService
import com.lgtm.android.data.service.ProfileService
import com.lgtm.android.data.service.SuggestionService
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

    @Provides
    @Singleton
    fun providesProfileService(retrofit: Retrofit): ProfileService =
        retrofit.create(ProfileService::class.java)


    @Provides
    @Singleton
    fun providesNotificationService(retrofit: Retrofit): NotificationService =
        retrofit.create(NotificationService::class.java)

    @Provides
    @Singleton
    fun providesSuggestionService(retrofit: Retrofit): SuggestionService =
        retrofit.create(SuggestionService::class.java)
}