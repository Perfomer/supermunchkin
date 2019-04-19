package com.volkovmedia.component.coredata.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.volkovmedia.commons.KeyEntity
import java.util.*

@Entity
data class Team(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,

    val name: String,

    val maxLevel: Int = 10,

    val lastGameDate: Date
) : KeyEntity<Long>