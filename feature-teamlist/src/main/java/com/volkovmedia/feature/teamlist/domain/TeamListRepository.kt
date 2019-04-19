package com.volkovmedia.feature.teamlist.domain

import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Team
import io.reactivex.Completable
import io.reactivex.Observable

internal interface TeamListRepository {

    fun getTeams(): Observable<List<TeamDto>>

    fun createTeam(team: Team): Completable

    fun updateTeam(team: Team): Completable

    fun removeTeam(team: Team): Completable

}