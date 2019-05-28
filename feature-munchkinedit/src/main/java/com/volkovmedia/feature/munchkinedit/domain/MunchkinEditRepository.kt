package com.volkovmedia.feature.munchkinedit.domain

import com.volkovmedia.component.data.model.entity.Munchkin
import io.reactivex.Completable
import io.reactivex.Single

internal interface MunchkinEditRepository {

    fun getMunchkin(munchkinId: Long): Single<Munchkin>

    fun saveMunchkin(munchkin: Munchkin): Completable

}