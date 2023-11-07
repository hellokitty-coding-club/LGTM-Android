package com.lgtm.domain.logging

import com.swm.logging.android.logging_scheme.ClickScheme

class HomeScreenClickScheme(
    titleName: String,
    age: String
) : ClickScheme() {

    init {
        setLoggingScheme(
            logName = "notificationClick",
            screenName = "HomeFragment",
            logVersion = "1",
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

        fun build(): HomeScreenClickScheme {
            return HomeScreenClickScheme(
                titleName, age
            )
        }
    }
}