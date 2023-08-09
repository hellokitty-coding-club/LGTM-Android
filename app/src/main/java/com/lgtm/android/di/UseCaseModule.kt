package com.lgtm.android.di

import com.lgtm.domain.repository.AuthRepository
import com.lgtm.domain.usecase.DeviceTokenManagerUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideDeviceTokenManagerUseCase(
        authRepository: AuthRepository
    ): DeviceTokenManagerUseCase =
        DeviceTokenManagerUseCase(authRepository)
}