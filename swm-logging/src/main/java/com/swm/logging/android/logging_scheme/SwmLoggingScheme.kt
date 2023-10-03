package com.swm.logging.android.logging_scheme

abstract class SwmLoggingScheme {
    open lateinit var eventLogName: String
    open lateinit var screenName: String
    open var logVersion: Int = 0
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
}