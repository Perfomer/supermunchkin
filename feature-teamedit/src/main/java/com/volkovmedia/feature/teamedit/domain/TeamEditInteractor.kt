package com.volkovmedia.feature.teamedit.domain

import com.volkovmedia.component.data.model.dto.TeamDto
import io.reactivex.Single

internal class TeamEditInteractor(private val repository: TeamEditRepository) {

    fun getTeam(teamId: Long) = repository.getTeam(teamId)

    fun saveTeam(team: TeamDto): Single<Long> {
        return if (team.id == 0L) repository.createTeam(team)
        else repository.editTeam(team)
    }

}