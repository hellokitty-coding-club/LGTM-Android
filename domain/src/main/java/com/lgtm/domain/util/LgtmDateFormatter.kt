package com.lgtm.domain.util

import java.time.format.DateTimeFormatter

val dotStyleFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd")
val isoStyleFormatter: DateTimeFormatter = DateTimeFormatter.ISO_DATE
