package com.volkovmedia.feature.battle.presentation.mvi

import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.battle.domain.model.Monster

internal data class BattleState(
    val isLoading: Boolean = false,

    val munchkins: List<Munchkin> = emptyList(),
    val monsters: List<Monster> = emptyList(),

    val munchkinsStrength: Int = 0,
    val monstersStrength: Int = 0
)