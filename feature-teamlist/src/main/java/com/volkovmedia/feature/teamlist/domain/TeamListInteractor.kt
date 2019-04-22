package com.volkovmedia.feature.teamlist.domain

import com.volkovmedia.component.data.model.entity.Team

internal class TeamListInteractor(private val repository: TeamListRepository) {

    fun getTeams() = repository.getTeams()

    fun removeTeam(team: Team) = repository.removeTeam(team)

}