package com.volkovmedia.feature.teamslist.domain

import com.volkovmedia.coredata.dao.TeamDao
import com.volkovmedia.coredata.model.entity.Team
import io.reactivex.Completable

class TeamsListInteractor(private val teamDao: TeamDao) {

    fun getTeams() = teamDao.getTeams()

    fun putTeam(team: Team) = Completable.fromAction { teamDao.put(team) }

    fun removeTeam(team: Team) = Completable.fromAction { teamDao.remove(team) }

}