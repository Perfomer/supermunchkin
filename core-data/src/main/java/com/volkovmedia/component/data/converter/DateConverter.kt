package com.volkovmedia.component.data.converter

import androidx.room.TypeConverter
import java.util.*

internal class DateConverter {

    @TypeConverter
    fun fromDate(date: Date) = date.time

    @TypeConverter
    fun fromTimestamp(timestamp: Long) = Date(timestamp)

}