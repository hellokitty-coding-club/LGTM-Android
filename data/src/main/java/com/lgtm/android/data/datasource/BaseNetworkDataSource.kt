package com.lgtm.android.data.datasource

import com.google.gson.Gson
import com.lgtm.android.data.model.response.BaseDTO
import com.lgtm.domain.entity.LgtmResponseException
import com.lgtm.domain.entity.ResponseCode
import retrofit2.Response

abstract class BaseNetworkDataSource {
    protected fun <T> checkResponse(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            val errorResponse: BaseDTO<*> = Gson().fromJson(errorBody, BaseDTO::class.java)
            throw LgtmResponseException(
                responseCode = ResponseCode.create(errorResponse.responseCode),
                httpCode = response.code(),
                message = errorResponse.message,
                cause = Throwable(errorBody),
            )
        }
    }
}



