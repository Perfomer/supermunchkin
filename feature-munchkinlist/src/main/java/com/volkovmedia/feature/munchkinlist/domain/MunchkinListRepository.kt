package com.volkovmedia.feature.munchkinlist.domain

import com.volkovmedia.component.coredata.model.entity.Munchkin
import io.reactivex.Completable
import io.reactivex.Observable

internal interface MunchkinListRepository {

    fun getMunchkins(teamId: Long): Observable<List<Munchkin>>

    fun updateMunchkin(munchkin: Munchkin): Completable

    fun createMunchkin(munchkin: Munchkin): Completable

    fun removeMunchkin(munchkin: Munchkin): Completable

}