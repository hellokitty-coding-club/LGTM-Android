package com.swm.logging.android

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface LoggingService {

    @POST("v1/log")
    suspend fun postExposureLogging(
        @Body exposureLogging: SwmLoggingScheme
    ): Response<BaseDTO>
}

data class BaseDTO(
    val success: Boolean,
    val responseCode: Int,
    val message: String,
    val data: String
)

