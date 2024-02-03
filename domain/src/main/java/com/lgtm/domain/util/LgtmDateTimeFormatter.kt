package com.lgtm.domain.util

import java.time.format.DateTimeFormatter
import java.util.Locale

val dotStyleDateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val isoStyleFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
val korean12HourTimeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("a h:mm").withLocale(Locale("ko", "KR"))