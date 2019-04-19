package com.volkovmedia.feature.munchkinlist.domain

import com.volkovmedia.component.common.KeyEntity
import com.volkovmedia.component.coredata.model.entity.Munchkin

data class MunchkinDto(
    val munchkin: Munchkin,
    val isLeader: Boolean = false
): KeyEntity<Long> {

    override val id = munchkin.id

}