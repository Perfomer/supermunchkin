package com.volkovmedia.feature.teamedit.domain

import com.volkovmedia.component.data.model.dto.TeamDto
import io.reactivex.Observable
import io.reactivex.Single

internal interface TeamEditRepository {

    fun getTeam(teamId: Long): Observable<TeamDto>

    fun createTeam(team: TeamDto): Single<Long>

    fun editTeam(teamDto: TeamDto): Single<Long>

}