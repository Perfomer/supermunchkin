package com.volkovmedia.coredata.converter

import androidx.room.TypeConverter
import com.volkovmedia.coredata.model.MunchkinRace

internal class MunchkinRaceConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinRace) = type.ordinal

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = MunchkinRace.values()[ordinal]

}