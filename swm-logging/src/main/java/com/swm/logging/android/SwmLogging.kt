package com.swm.logging.android

import kotlin.properties.Delegates

object SwmLogging {

    private lateinit var appVersion: String
    private lateinit var osName: String
    private lateinit var endpoint: String


    //    var endpoint by Delegates.notNull<String>()
//    var header by Delegates.notNull<MutableMap<String, String>>()
//    var requestBody by Delegates´.notNull<MutableMap<String, Any>>()
//    fun setEndpoint(url: String) {
//        endpoint = url
//    }
//
//    // 1. Initial Time
//    fun setMandatoryFields(header: Map<String, String>?) {
//        header?.forEach { this.header[it.key] = it.value }
//    }


    lateinit var test: ExposureLogging

    fun shotExposureLogging(exposureLogging: ExposureLogging) {
        test = exposureLogging
        //todo endpoint 초기화 안돼있으면 crash
        //loggingService.postExposureLogging(exposureLogging)
    }


    fun init(appVersion: String, osName: String, endpoint: String) {
        this.appVersion = appVersion
        this.osName = osName
        this.endpoint = endpoint
    }
}


abstract class ExposureLogging : SwmLoggingScheme()
abstract class ClickLogging : SwmLoggingScheme()

abstract class SwmLoggingScheme {
    lateinit var eventLogName: String
    lateinit var screenName: String
    var logVersion by Delegates.notNull<Int>()
}
