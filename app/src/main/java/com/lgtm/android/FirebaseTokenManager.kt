package com.lgtm.android

import android.content.ContentValues
import android.util.Log
import com.lgtm.domain.firebase.FakeFirebaseTokenManager
import com.lgtm.domain.repository.AuthRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseTokenManager @Inject constructor(
    private val authRepository: AuthRepository,
) : FakeFirebaseTokenManager {

    fun patchFcmToken(fcmToken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
                authRepository.patchDeviceToken(fcmToken)
            }.onSuccess {
                Log.d(ContentValues.TAG, "onNewToken: success")
            }.onFailure {
                Log.e(ContentValues.TAG, "onNewToken: failure ${it.message}")
            }
        }
    }
}