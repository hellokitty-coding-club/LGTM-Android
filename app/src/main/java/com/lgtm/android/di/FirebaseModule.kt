package com.lgtm.android.di

import com.lgtm.android.LGTMFirebaseMessagingService
import com.lgtm.domain.firebase.LgtmMessagingService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    fun provideLGTMFirebaseMessagingService(): LgtmMessagingService =
        LGTMFirebaseMessagingService()
}