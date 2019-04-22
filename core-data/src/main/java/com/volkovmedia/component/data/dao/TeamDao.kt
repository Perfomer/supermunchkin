package com.volkovmedia.component.data.dao

import androidx.room.Dao
import androidx.room.Query
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Team
import io.reactivex.Observable

@Dao
interface TeamDao : BaseDao<Team> {

    @Query("SELECT * FROM Team ORDER BY lastGameDate DESC")
    fun getTeams(): Observable<List<Team>>

    @Query("SELECT * FROM Team ORDER BY lastGameDate DESC")
    fun getTeamsWithParticipants(): Observable<List<TeamDto>>

    @Query("SELECT * FROM Team WHERE id = :teamId ORDER BY lastGameDate DESC")
    fun getTeam(teamId: Long): Observable<Team>

    @Query("SELECT * FROM Team WHERE id = :teamId ORDER BY lastGameDate DESC")
    fun getTeamWithParticipants(teamId: Long): Observable<TeamDto>

}