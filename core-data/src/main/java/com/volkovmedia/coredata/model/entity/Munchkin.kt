package com.volkovmedia.coredata.model.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.volkovmedia.commons.KeyEntity
import com.volkovmedia.coredata.model.MunchkinClass
import com.volkovmedia.coredata.model.MunchkinGender
import com.volkovmedia.coredata.model.MunchkinRace

@Entity(
    foreignKeys = [ForeignKey(
        entity = Team::class,
        parentColumns = ["id"],
        childColumns = ["teamId"],
        onDelete = CASCADE
    )]
)
data class Munchkin(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,

    val teamId: Long,

    val name: String,

    val level: Int = 1,

    val gear: Int = 0,

    val gender: MunchkinGender = MunchkinGender.MALE,

    val firstClass: MunchkinClass? = null,

    val secondClass: MunchkinClass? = null,

    val firstRace: MunchkinRace = MunchkinRace.HUMAN,

    val secondRace: MunchkinRace? = null
) : KeyEntity<Long> {

    @Ignore
    val strength: Int = level + gear

}