package com.volkovmedia.feature.teamslist.presentation.mvi

import com.volkovmedia.coredata.model.dto.TeamDto

data class TeamsListState(
    val isLoading: Boolean = false,
    val payload: List<TeamDto> = emptyList()
)