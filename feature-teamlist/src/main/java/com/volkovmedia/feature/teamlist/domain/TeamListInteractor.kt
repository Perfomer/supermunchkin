package com.volkovmedia.feature.teamlist.domain

import com.volkovmedia.component.common.util.currentDate
import com.volkovmedia.component.data.model.entity.Team

internal class TeamListInteractor(private val repository: TeamListRepository) {

    fun getTeams() = repository.getTeams()

    fun createTeam(name: String) = repository.createTeam(Team(name = name, lastGameDate = currentDate))

    fun updateTeam(team: Team) = repository.updateTeam(team)

    fun removeTeam(team: Team) = repository.removeTeam(team)

}