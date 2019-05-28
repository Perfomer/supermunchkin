package com.volkovmedia.feature.munchkinedit.presentation.mvi

import com.volkovmedia.component.data.model.entity.Munchkin

internal data class MunchkinEditState(
    val isLoading: Boolean = false,
    val munchkin: Munchkin = Munchkin(name = "", teamId = 0),
    val superMunchkinEnabled: Boolean = false,
    val halfBloodEnabled: Boolean = false
)