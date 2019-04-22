package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.feature.munchkinlist.domain.model.LocalTeamDto

internal data class MunchkinListState(
    val isLoading: Boolean = false,
    val isCollapsed: Boolean = false,
    val teamDto: LocalTeamDto = LocalTeamDto()
)