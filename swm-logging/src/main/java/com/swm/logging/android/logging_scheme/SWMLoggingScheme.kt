package com.swm.logging.android.logging_scheme

import com.swm.logging.android.SWMLogging

abstract class SWMLoggingScheme {
    open lateinit var eventLogName: String
    open lateinit var screenName: String
    open lateinit var logVersion: String
    private val osVersionAndName: String = SWMLogging.getOsNameAndVersion()
    private var logData: MutableMap<String, Any>? = mutableMapOf()
    private val sessionID = SWMLogging.getUUID()
    private val userID = SWMLogging.getUserID()
    private val deviceModel = SWMLogging.getDeviceModel()
    private val appVersion = SWMLogging.getAppVersion()
    private val region = SWMLogging.getRegion()
    fun setLoggingScheme(
        evenLogName: String,
        screenName: String,
        logVersion: String,
        logData: MutableMap<String, Any>?,
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
