package com.volkovmedia.feature.munchkinlist.domain

import com.volkovmedia.component.coredata.model.MunchkinGender
import com.volkovmedia.component.coredata.model.entity.Munchkin

internal class MunchkinListInteractor(private val repository: MunchkinListRepository) {

    fun getMunchkins(teamId: Long) = repository.getMunchkins(teamId)
        .map { it.map { MunchkinDto(it, false) } }

    fun createMunchkin(teamId: Long, name: String, gender: MunchkinGender) = repository.createMunchkin(
        Munchkin(
            teamId = teamId,
            name = name,
            gender = gender
        )
    )

    fun putMunchkin(munchkin: Munchkin) = repository.updateMunchkin(munchkin)

    fun removeMunchkin(munchkin: Munchkin) = repository.removeMunchkin(munchkin)

}