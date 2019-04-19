package com.volkovmedia.component.coredata.model.dto

import androidx.room.Embedded
import androidx.room.Ignore
import androidx.room.Relation
import com.volkovmedia.commons.KeyEntity
import com.volkovmedia.component.coredata.model.entity.Munchkin
import com.volkovmedia.component.coredata.model.entity.Team

data class TeamDto(
    @Embedded
    val team: Team,

    @Relation(parentColumn = "id", entityColumn = "teamId")
    val participants: List<Munchkin>
) : KeyEntity<Long> {

    @Ignore
    override val id = team.id

}