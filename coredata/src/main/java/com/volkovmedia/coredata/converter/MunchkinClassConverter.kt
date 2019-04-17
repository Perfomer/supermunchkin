package com.volkovmedia.coredata.converter

import androidx.room.TypeConverter
import com.volkovmedia.coredata.model.MunchkinClass

internal class MunchkinClassConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinClass) = type.ordinal

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = MunchkinClass.values()[ordinal]

}