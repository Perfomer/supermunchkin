package com.volkovmedia.feature.munchkinlist.domain

import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Munchkin
import io.reactivex.Completable
import io.reactivex.Observable

internal interface MunchkinListRepository {

    fun getTeam(teamId: Long): Observable<TeamDto>

    fun updateMunchkin(munchkin: Munchkin): Completable

    fun removeMunchkin(munchkin: Munchkin): Completable

}