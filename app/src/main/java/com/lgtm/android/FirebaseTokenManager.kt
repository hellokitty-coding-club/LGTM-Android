package com.lgtm.android

import android.content.ContentValues
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FirebaseTokenManager @Inject constructor(
//    authRepository: AuthRepository,
) {

    fun fetchFcmToken(fcmToken: String) {
        CoroutineScope(Dispatchers.IO).launch {
            kotlin.runCatching {
//               authRepository.fetchFcmToken(fcmToken)
            }.onSuccess {
                Log.d(ContentValues.TAG, "onNewToken: success")
            }.onFailure {
                Log.d(ContentValues.TAG, "onNewToken: failure ${it.message}")
            }
        }

    }
}