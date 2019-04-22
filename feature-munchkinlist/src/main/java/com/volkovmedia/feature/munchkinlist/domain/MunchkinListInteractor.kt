package com.volkovmedia.feature.munchkinlist.domain

import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinlist.domain.model.LocalTeamDto
import com.volkovmedia.feature.munchkinlist.domain.model.MunchkinDto

internal class MunchkinListInteractor(private val repository: MunchkinListRepository) {

    fun getTeam(teamId: Long) = repository.getTeam(teamId)
        .map {
            val leader = it.participants.maxBy { it.strength }
            val newParticipants = it.participants.map { MunchkinDto(it, it.id == leader?.id) }

            return@map LocalTeamDto(team = it.team, participants = newParticipants)
        }

    fun putMunchkin(munchkin: Munchkin) = repository.updateMunchkin(munchkin)

    fun removeMunchkin(munchkin: Munchkin) = repository.removeMunchkin(munchkin)

}