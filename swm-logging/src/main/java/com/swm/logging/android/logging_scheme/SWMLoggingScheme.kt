package com.swm.logging.android.logging_scheme

import com.swm.logging.android.SWMLogging

abstract class SWMLoggingScheme {
    open lateinit var eventLogName: String
    open lateinit var screenName: String
    open var logVersion: Int = 0
    private val osVersionAndName: String = SWMLogging.getOsNameAndVersion()
    private var logData: MutableMap<String, Any>? = mutableMapOf()
    fun setLoggingScheme(
        evenLogName: String,
        screenName: String,
        logVersion: Int,
        logData: MutableMap<String, Any>?
    ) {
        this.eventLogName = evenLogName
        this.screenName = screenName
        this.logVersion = logVersion
        this.logData = logData
    }

    fun getLogData(): MutableMap<String, Any>? {
        return logData
    }
}
