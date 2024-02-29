package com.lgtm.android.data.model.mapper

import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import java.time.LocalDateTime
import java.time.format.DateTimeParseException

fun parseDate(date: String?): LocalDateTime? {
    return try {
        checkNotNull(date)
        LocalDateTime.parse(date)
    } catch (e: DateTimeParseException) {
        Firebase.crashlytics.log("$e / Wrong Date format : $date")
        null
    } catch (e: IllegalStateException) {
        Firebase.crashlytics.log("$e / created value might be null : $date")
        null
    }
}