package com.volkovmedia.feature.teamedit.data

import com.volkovmedia.component.data.dao.MunchkinDao
import com.volkovmedia.component.data.dao.TeamDao
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.feature.teamedit.domain.TeamEditRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

internal class TeamEditDataSource(
    private val teamDao: TeamDao,
    private val munchkinDao: MunchkinDao
) : TeamEditRepository {

    override fun getTeam(teamId: Long) = teamDao.getTeamWithParticipants(teamId)

    override fun createTeam(team: TeamDto) = Single.fromCallable {
        val teamId = teamDao.insert(team.team)

        team.participants
            .map { it.copy(teamId = teamId) }
            .forEach { munchkinDao.insert(it) }

        return@fromCallable teamId
    }

    override fun editTeam(teamDto: TeamDto) = Single.fromCallable {
        teamDao.update(teamDto.team)

        teamDto.participants.forEach {
            if (it.id == 0L) munchkinDao.insert(it)
            else munchkinDao.update(it)
        }

        return@fromCallable teamDto.id
    }

}