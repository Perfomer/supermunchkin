package com.volkovmedia.component.data.model.dto

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.volkovmedia.component.common.KeyEntity
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.component.data.model.entity.Team

data class TeamDto(
    @Embedded
    val team: Team = Team(),

    @Relation(parentColumn = "id", entityColumn = "teamId")
    val participants: List<Munchkin> = emptyList()
) : KeyEntity<Long> {

    @Ignore
    override val id = team.id

}