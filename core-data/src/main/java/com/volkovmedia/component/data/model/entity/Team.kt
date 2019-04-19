package com.volkovmedia.component.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.volkovmedia.component.common.KeyEntity
import java.util.*

@Entity
data class Team(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,

    val name: String,

    val maxLevel: Int = 10,

    val lastGameDate: Date
) : KeyEntity<Long>