package com.lgtm.android.data.datasource

import com.lgtm.android.data.model.HttpResponseException
import com.lgtm.android.data.model.HttpResponseStatus
import retrofit2.Response

abstract class BaseNetworkDataSource {
    protected fun <T> checkResponse(response: Response<T>): T {
        if (response.isSuccessful) {
            return response.body()!!
        } else {
            val errorBody = response.errorBody()?.string()
            throw HttpResponseException(
                status = HttpResponseStatus.create(response.code()),
                httpCode = response.code(),
                errorRequestUrl = "errorBody 형식 맞추면 변경", //todo
                msg = "Http Request Failed (${response.code()}) ${response.message()}, $errorBody",
                cause = Throwable(errorBody),
            )
        }
    }
}



