package com.lgtm.domain.logging

import com.swm.logging.android.logging_scheme.ExposureScheme

class HomeScreenExposureScheme(
    titleName: String,
    age: String
) : ExposureScheme() {

    init {
        setLoggingScheme(
            evenLogName = "missionClick",
            screenName = "home",
            logVersion = 1,
            logData = mutableMapOf(
                "titleName" to titleName,
                "age" to age
            )
        )
    }

    class Builder {
        private lateinit var titleName: String
        private lateinit var age: String
        fun setTitleName(titleName: String): Builder {
            this.titleName = titleName
            return this
        }

        fun setAge(age: String): Builder {
            this.age = age
            return this
        }

        fun build(): HomeScreenExposureScheme {
            return HomeScreenExposureScheme(
                titleName, age
            )
        }
    }
}