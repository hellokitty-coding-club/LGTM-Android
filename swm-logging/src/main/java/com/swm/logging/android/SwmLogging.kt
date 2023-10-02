package com.swm.logging.android

object SwmLogging {
    //    var endpoint by Delegates.notNull<String>()
//    var header by Delegates.notNull<MutableMap<String, String>>()
//    var requestBody by Delegates.notNull<MutableMap<String, Any>>()
//    fun setEndpoint(url: String) {
//        endpoint = url
//    }
//
//    // 1. Initial Time
//    fun setMandatoryFields(header: Map<String, String>?) {
//        header?.forEach { this.header[it.key] = it.value }
//    }


    lateinit var test :ExposureLogging
    fun shotExposureLogging(exposureLogging: ExposureLogging) {
        test = exposureLogging
        println(exposureLogging)
    }
}

abstract class ExposureLogging

