package com.volkovmedia.feature.munchkinlist.data

import com.volkovmedia.component.data.dao.MunchkinDao
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinlist.domain.MunchkinListRepository
import io.reactivex.Completable

internal class MunchkinListDataSource(private val munchkinDao: MunchkinDao) : MunchkinListRepository {

    override fun getMunchkins(teamId: Long) = munchkinDao.getMunchkins(teamId)

    override fun updateMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.update(munchkin) }

    override fun createMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.insert(munchkin) }

    override fun removeMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.remove(munchkin) }

}