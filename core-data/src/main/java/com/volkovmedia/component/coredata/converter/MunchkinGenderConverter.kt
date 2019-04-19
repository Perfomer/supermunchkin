package com.volkovmedia.component.coredata.converter

import androidx.room.TypeConverter
import com.volkovmedia.component.coredata.model.MunchkinGender

internal class MunchkinGenderConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinGender) = type.ordinal

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = MunchkinGender.values()[ordinal]

}