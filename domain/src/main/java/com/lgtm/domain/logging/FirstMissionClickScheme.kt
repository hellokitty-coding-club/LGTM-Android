package com.lgtm.domain.logging

import com.swm.logging.android.logging_scheme.ClickScheme

class FirstMissionClickScheme(
    missionId : Int,
    spendingTime : Long
) : ClickScheme() {

    init {
        setLoggingScheme(
            eventLogName = "firstMissionClick",
            screenName = "HomeFragment",
            logVersion = "1",
            logData = mutableMapOf(
                "missionId" to missionId,
                "spendingTime" to spendingTime
            )
        )
    }

    class Builder {
        private var missionId : Int = 0
        private var spendingTime : Long = 0

        fun setMissionId(missionId : Int): Builder {
            this.missionId = missionId
            return this
        }

        fun setSpendingTime(spendingTime : Long): Builder {
            this.spendingTime = spendingTime
            return this
        }

        fun build(): FirstMissionClickScheme {
            return FirstMissionClickScheme(
                missionId,
                spendingTime
            )
        }
    }
}