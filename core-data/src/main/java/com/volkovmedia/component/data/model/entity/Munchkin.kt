package com.volkovmedia.component.data.model.entity

import androidx.room.*
import androidx.room.ForeignKey.CASCADE
import com.volkovmedia.component.common.KeyEntity
import com.volkovmedia.component.data.model.MunchkinGender

@Entity(
    foreignKeys = [ForeignKey(
        entity = Team::class,
        parentColumns = ["id"],
        childColumns = ["teamId"],
        onDelete = CASCADE
    )],
    indices = [Index(value = ["teamId", "name"], unique = true)]
)
data class Munchkin(
    @PrimaryKey(autoGenerate = true)
    override val id: Long = 0L,

    val teamId: Long,

    val name: String,

    val level: Int = 1,

    val gear: Int = 0,

    val gender: MunchkinGender = MunchkinGender.MALE
) : KeyEntity<Long> {

    @Ignore
    val strength: Int = level + gear

}