package com.lgtm.domain.logging

import com.lgtm.domain.server_drive_ui.SduiContent
import com.swm.logging.android.logging_scheme.SWMLoggingScheme

class HomeMissionClickScheme(
    sduiContent: SduiContent,
) : SWMLoggingScheme() {

    init {
        setLoggingScheme(
            eventLogName = "homeMissionClick",
            screenName = "HomeFragment",
            logVersion = "1",
            logData = mutableMapOf(
                "sduiContent" to sduiContent
            )
        )
    }

    class Builder {
        private lateinit var sduiContent: SduiContent

        fun setMissionContent(sduiContent: SduiContent): Builder {
            this.sduiContent = sduiContent
            return this
        }

        fun build(): HomeMissionClickScheme {
            return HomeMissionClickScheme(
                sduiContent
            )
        }
    }
}