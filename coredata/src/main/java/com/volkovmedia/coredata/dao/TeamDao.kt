package com.volkovmedia.coredata.dao

import androidx.room.Dao
import androidx.room.Query
import com.volkovmedia.coredata.model.entity.Team
import io.reactivex.Observable

@Dao
interface TeamDao: BaseDao<Team> {

    @Query("SELECT * FROM Team ORDER BY lastGameDate DESC")
    fun getTeams(): Observable<List<Team>>

}