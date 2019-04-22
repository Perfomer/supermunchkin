package com.volkovmedia.feature.munchkinlist.data

import com.volkovmedia.component.data.dao.MunchkinDao
import com.volkovmedia.component.data.dao.TeamDao
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinlist.domain.MunchkinListRepository
import io.reactivex.Completable
import io.reactivex.Observable

internal class MunchkinListDataSource(
    private val teamDao: TeamDao,
    private val munchkinDao: MunchkinDao
) : MunchkinListRepository {

    override fun getTeam(teamId: Long) = teamDao.getTeamWithParticipants(teamId)

    override fun updateMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.update(munchkin) }

    override fun removeMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.remove(munchkin) }

}