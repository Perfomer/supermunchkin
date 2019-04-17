package com.volkovmedia.feature.teamlist.data

import com.volkovmedia.coredata.dao.TeamDao
import com.volkovmedia.coredata.model.entity.Team
import com.volkovmedia.feature.teamlist.domain.TeamListRepository
import io.reactivex.Completable

internal class TeamListDataSource(private val teamDao: TeamDao) : TeamListRepository {

    override fun getTeams() = teamDao.getTeamsWithParticipants()

    override fun putTeam(team: Team) = Completable.fromAction { teamDao.put(team) }

    override fun removeTeam(team: Team) = Completable.fromAction { teamDao.remove(team) }

}