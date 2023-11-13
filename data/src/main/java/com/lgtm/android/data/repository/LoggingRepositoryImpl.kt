package com.lgtm.android.data.repository

import com.lgtm.domain.repository.LoggingRepository
import com.swm.logging.android.SWMLogging
import com.swm.logging.android.logging_scheme.SWMLoggingScheme
import javax.inject.Inject

class LoggingRepositoryImpl @Inject constructor() : LoggingRepository {
    override fun shotSwmLogging(scheme: SWMLoggingScheme) {
        SWMLogging.logEvent(scheme)
    }
}