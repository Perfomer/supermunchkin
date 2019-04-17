package com.volkovmedia.feature.teamslist.presentation.mvi

import com.volkovmedia.coredata.model.entity.Team

data class TeamsListState(
    val isLoading: Boolean = false,
    val payload: List<Team> = emptyList()
)