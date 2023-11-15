package com.lgtm.domain.repository

import com.swm.logging.android.logging_scheme.SWMLoggingScheme

interface LoggingRepository {
    fun shotSwmLogging(scheme: SWMLoggingScheme)
}