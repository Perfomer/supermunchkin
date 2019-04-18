package com.volkovmedia.coredata.converter

import androidx.room.TypeConverter
import com.volkovmedia.coredata.model.MunchkinClass

internal class MunchkinClassConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinClass?) = type?.ordinal ?: NO_VALUE

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = if (ordinal == NO_VALUE) null else MunchkinClass.values()[ordinal]

    private companion object {

        private const val NO_VALUE = -1

    }

}