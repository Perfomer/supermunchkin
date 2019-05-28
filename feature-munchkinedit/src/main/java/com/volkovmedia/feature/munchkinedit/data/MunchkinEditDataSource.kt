package com.volkovmedia.feature.munchkinedit.data

import com.volkovmedia.component.data.dao.MunchkinDao
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinedit.domain.MunchkinEditRepository
import io.reactivex.Completable

internal class MunchkinEditDataSource(private val munchkinDao: MunchkinDao) : MunchkinEditRepository {

    override fun getMunchkin(munchkinId: Long) = munchkinDao.getMunchkin(munchkinId)

    override fun saveMunchkin(munchkin: Munchkin) = Completable.fromAction { munchkinDao.insert(munchkin) }

}