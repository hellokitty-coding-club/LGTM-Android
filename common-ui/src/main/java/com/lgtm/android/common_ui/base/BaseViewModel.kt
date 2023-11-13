package com.lgtm.android.common_ui.base

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lgtm.domain.entity.LgtmResponseException
import kotlinx.coroutines.CoroutineExceptionHandler

abstract class BaseViewModel : ViewModel() {

    private val _moveToSignIn = MutableLiveData<Boolean>()
    val moveToSignIn: LiveData<Boolean> = _moveToSignIn

    private val _unknownError = MutableLiveData<String>() // make toast
    val unknownError: LiveData<String> = _unknownError

    val lgtmErrorHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "[LgtmNetworkError] ${throwable.message}")
        if (throwable is LgtmResponseException) {
            when (throwable.httpCode) {
                401 -> _moveToSignIn.postValue(true)
                500 -> _unknownError.postValue("알 수 없는 오류가 발생했습니다.")
                in 400..499 -> _unknownError.postValue(throwable.message)
                else -> Log.e(
                    TAG,
                    "[LgtmNetworkError] Response code : ${throwable.responseCode}, Error Message :  ${throwable.message}"
                )
            }
        }
    }
}
