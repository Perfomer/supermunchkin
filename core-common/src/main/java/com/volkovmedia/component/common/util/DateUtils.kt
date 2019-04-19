package com.volkovmedia.component.common.util

import java.text.SimpleDateFormat
import java.util.*

val currentDate: Date
    get() = Calendar.getInstance().time

fun Date.format(format: String = "dd-MM-yy"): String {
    return SimpleDateFormat(format, Locale.getDefault()).format(this)
}