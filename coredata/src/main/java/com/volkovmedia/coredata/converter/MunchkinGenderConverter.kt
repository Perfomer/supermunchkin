package com.volkovmedia.coredata.converter

import androidx.room.TypeConverter
import com.volkovmedia.coredata.model.MunchkinGender
import com.volkovmedia.coredata.model.MunchkinRace

internal class MunchkinGenderConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinGender) = type.ordinal

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = MunchkinGender.values()[ordinal]

}