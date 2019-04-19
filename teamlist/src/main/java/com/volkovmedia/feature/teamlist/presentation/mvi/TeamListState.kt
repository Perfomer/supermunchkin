package com.volkovmedia.feature.teamlist.presentation.mvi

import com.volkovmedia.component.coredata.model.dto.TeamDto

internal data class TeamListState(
    val isLoading: Boolean = false,
    val payload: List<TeamDto> = emptyList()
)