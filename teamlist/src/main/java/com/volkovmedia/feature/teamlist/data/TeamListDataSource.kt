package com.volkovmedia.feature.teamlist.data

import com.volkovmedia.component.coredata.dao.TeamDao
import com.volkovmedia.component.coredata.model.entity.Team
import com.volkovmedia.feature.teamlist.domain.TeamListRepository
import io.reactivex.Completable

internal class TeamListDataSource(private val teamDao: TeamDao) : TeamListRepository {

    override fun getTeams() = teamDao.getTeamsWithParticipants()

    override fun updateTeam(team: Team) = Completable.fromAction { teamDao.update(team) }

    override fun createTeam(team: Team) = Completable.fromAction { teamDao.insert(team) }

    override fun removeTeam(team: Team) = Completable.fromAction { teamDao.remove(team) }

}