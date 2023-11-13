package com.lgtm.domain.logging

import com.swm.logging.android.logging_scheme.ClickScheme
import java.lang.reflect.Type

class CommonLoggingScheme(
    eventLogName: String,
    screenName: String,
    logVersion: String,
    logData: Map<String, Any>,
) : ClickScheme() {

    init {
        setLoggingScheme(
            eventLogName = eventLogName,
            screenName = screenName,
            logVersion = logVersion,
            logData = logData.toMutableMap()
        )
    }

    class Builder {
        private lateinit var eventLogName: String
        private lateinit var screenName: String
        private var logVersion: String = "1"
        private var map = mutableMapOf<String, Any>()

        fun setEventLogName(eventLogName: String): Builder {
            this.eventLogName = eventLogName
            return this
        }

        fun setScreenName(screenName: String): Builder {
            this.screenName = screenName
            return this
        }

        fun setScreenName(type: Type): Builder {
            check(type is Class<*>)
            this.screenName = type.simpleName
            return this
        }


        fun setLogData(map: Map<String, Any>): Builder {
            this.map = map.toMutableMap()
            return this
        }

        fun setLogVersion(logVersion: Int): Builder {
            this.logVersion = logVersion.toString()
            return this
        }

        fun build(): CommonLoggingScheme {
            check(::eventLogName.isInitialized) { "eventLogName is not initialized" }
            check(::screenName.isInitialized) { "screenName is not initialized" }

            return CommonLoggingScheme(
                eventLogName,
                screenName,
                logVersion,
                map
            )
        }
    }
}