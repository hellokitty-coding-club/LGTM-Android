package com.swm.logging.android

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoggingService {

    @POST("v1/log")
    suspend fun postExposureLogging(
        @Body exposureLogging: ExposureLogging
    ): Response<BaseDTO<Data>>
}

data class BaseDTO<T>(
    val success: Boolean,
    val responseCode: Int,
    val message: String,
    val `data`: T
)

data class Data(val string: String)