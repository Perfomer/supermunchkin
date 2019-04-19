package com.volkovmedia.component.data.converter

import androidx.room.TypeConverter
import com.volkovmedia.component.data.model.MunchkinRace

internal class MunchkinRaceConverter {

    @TypeConverter
    fun fromEnum(type: MunchkinRace?) = type?.ordinal ?: NO_VALUE

    @TypeConverter
    fun fromOrdinal(ordinal: Int) = if (ordinal == NO_VALUE) null else MunchkinRace.values()[ordinal]

    private companion object {

        private const val NO_VALUE = -1

    }

}