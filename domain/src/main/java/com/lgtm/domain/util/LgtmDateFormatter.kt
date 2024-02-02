package com.lgtm.domain.util

import java.time.format.DateTimeFormatter
import java.util.Locale

val dotStyleFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val isoStyleFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
val timeFormatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("a h:mm").withLocale(Locale("ko", "KR"))