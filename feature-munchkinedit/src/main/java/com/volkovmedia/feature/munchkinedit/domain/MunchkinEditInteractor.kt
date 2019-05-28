package com.volkovmedia.feature.munchkinedit.domain

import com.volkovmedia.component.data.model.entity.Munchkin

internal class MunchkinEditInteractor(private val repository: MunchkinEditRepository) {

    fun getMunchkin(munchkinId: Long) = repository.getMunchkin(munchkinId)

    fun saveMunchkin(munchkin: Munchkin) = repository.saveMunchkin(munchkin)

}