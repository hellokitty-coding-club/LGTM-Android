package com.lgtm.domain.logging

import com.swm.logging.android.ExposureLogging

class HomeScreenExposureLogging(title: String) : ExposureLogging() {

    class Builder {
        private lateinit var title: String

        fun setTitle(title: String) {
            this.title = title
        }

        fun build(): HomeScreenExposureLogging {
            return HomeScreenExposureLogging(title)
        }
    }
}