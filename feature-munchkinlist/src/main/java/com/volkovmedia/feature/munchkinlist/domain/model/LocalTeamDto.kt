package com.volkovmedia.feature.munchkinlist.domain.model

import com.volkovmedia.component.common.KeyEntity
import com.volkovmedia.component.data.model.entity.Team

data class LocalTeamDto(
    val team: Team = Team(),
    val participants: List<MunchkinDto> = emptyList()
) : KeyEntity<Long> {

    override val id = team.id

}