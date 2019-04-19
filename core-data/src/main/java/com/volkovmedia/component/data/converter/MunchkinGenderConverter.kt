package com.volkovmedia.component.data.converter

import androidx.room.TypeConverter
import com.volkovmedia.component.data.model.MunchkinGender

internal class MunchkinGenderConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinGender) = type.ordinal

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = MunchkinGender.values()[ordinal]

}