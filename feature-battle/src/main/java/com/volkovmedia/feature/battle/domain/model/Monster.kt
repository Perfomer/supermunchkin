package com.volkovmedia.feature.battle.domain.model

import com.volkovmedia.component.common.KeyEntity

internal data class Monster(
    override val id: Long,
    val level: Int,
    val bonuses: Int
): KeyEntity<Long> {

    val strength: Int = level + bonuses

}