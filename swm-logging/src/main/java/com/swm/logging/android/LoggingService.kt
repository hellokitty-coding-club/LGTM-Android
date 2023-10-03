package com.swm.logging.android

import com.swm.logging.android.logging_scheme.SwmLoggingScheme
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface LoggingService {

    @POST("{loggingEndpoint}")
    suspend fun postExposureLogging(
        @Path("loggingEndpoint") loggingEndpoint: String,
        @Body exposureLogging: SwmLoggingScheme
    ): Response<BaseDTO>
}

