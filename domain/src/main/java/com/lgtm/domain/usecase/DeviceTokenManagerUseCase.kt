package com.lgtm.domain.usecase

import com.lgtm.domain.firebase.FakeFirebaseTokenManager
import com.lgtm.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeviceTokenManagerUseCase @Inject constructor(
    private val authRepository: AuthRepository,
) : FakeFirebaseTokenManager {

    fun patchFcmToken(fcmToken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                authRepository.patchDeviceToken(fcmToken)
            }.onSuccess {
                println("success")
            }.onFailure {
                println("fail")
            }
        }
    }
}
