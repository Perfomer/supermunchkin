package com.volkovmedia.component.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.volkovmedia.component.data.model.entity.Munchkin
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface MunchkinDao : BaseDao<Munchkin> {

    @Query("SELECT * FROM Munchkin WHERE teamId = :teamId")
    fun getMunchkins(teamId: Long): Observable<List<Munchkin>>

    @Query("SELECT * FROM Munchkin WHERE id = :munchkinId")
    fun getMunchkin(munchkinId: Long): Single<Munchkin>

}