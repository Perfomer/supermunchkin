package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.feature.munchkinlist.domain.MunchkinDto

internal data class MunchkinListState(
    val isLoading: Boolean = false,
    val teamName: String = "",
    val payload: List<MunchkinDto> = emptyList()
)