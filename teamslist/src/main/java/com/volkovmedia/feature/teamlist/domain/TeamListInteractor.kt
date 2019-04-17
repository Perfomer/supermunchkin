package com.volkovmedia.feature.teamlist.domain

import com.volkovmedia.coredata.model.entity.Team

internal class TeamListInteractor(private val repository: TeamListRepository) {

    fun getTeams() = repository.getTeams()

    fun putTeam(team: Team) = repository.putTeam(team)

    fun removeTeam(team: Team) = repository.removeTeam(team)

}