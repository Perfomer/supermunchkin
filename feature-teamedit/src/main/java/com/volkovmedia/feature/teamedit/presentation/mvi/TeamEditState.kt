package com.volkovmedia.feature.teamedit.presentation.mvi

import com.volkovmedia.component.common.util.currentDate
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Team

internal data class TeamEditState(
    val isLoading: Boolean = false,
    val teamDto: TeamDto = TeamDto(
        team = Team(name = "", lastGameDate = currentDate),
        participants = emptyList()
    )
)