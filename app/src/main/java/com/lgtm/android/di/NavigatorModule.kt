package com.lgtm.android.di

import com.lgtm.android.common_ui.navigator.FakeSignInNavigator
import com.lgtm.android.navigator.SignInNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@InstallIn(SingletonComponent::class)
@Module
interface NavigatorModule {
    @Binds
    fun bindsSignInNavigator(
        signInNavigator: SignInNavigator
    ): FakeSignInNavigator

}