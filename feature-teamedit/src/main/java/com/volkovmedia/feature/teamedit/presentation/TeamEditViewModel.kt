package com.volkovmedia.feature.teamedit.presentation

import com.volkovmedia.component.common.mvi.MviViewModel
import com.volkovmedia.component.common.util.toObservable
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.component.data.model.entity.Team
import com.volkovmedia.feature.teamedit.domain.TeamEditInteractor
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditAction
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditAction.*
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditIntent
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditState
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditSubscription
import io.reactivex.Observable

internal class TeamEditViewModel(
    private val teamId: Long?,
    private val interactor: TeamEditInteractor
) : MviViewModel<TeamEditIntent, TeamEditAction, TeamEditState, TeamEditSubscription>(
    initialState = TeamEditState()
) {

    override fun act(state: TeamEditState, intent: TeamEditIntent) = when (intent) {
        TeamEditIntent.LoadData -> {
            if (teamId == null) Observable.empty()
            else interactor.getTeam(teamId)
                .flatMap<TeamEditAction> { state.toShowDataAction(it.team, it.participants) }
                .startWith(DataLoadingStarted)
        }

        is TeamEditIntent.EditTeamName -> {
            state.toShowDataAction(team = state.teamDto.team.copy(name = intent.name))
        }

        is TeamEditIntent.AddMunchkin -> {
            state.toShowDataAction(participants = state.teamDto.participants + intent.munchkin)
        }

        is TeamEditIntent.EditMunchkin -> {
            val updatedParticipants = state.teamDto.participants.map {
                if (it.name == intent.oldName) it.copy(name = intent.newName, gender = intent.newGender)
                else it
            }

            state.toShowDataAction(participants = updatedParticipants)
        }

        is TeamEditIntent.RemoveMunchkin -> {
            state.toShowDataAction(participants = state.teamDto.participants - intent.munchkin)
        }

        TeamEditIntent.SaveTeamDetails -> {
            interactor.saveTeam(state.teamDto)
                .toObservable()
                .map<TeamEditAction> { TeamSavingSucceed(it) }
        }
    }

    override fun reduce(oldState: TeamEditState, action: TeamEditAction) = when (action) {
        DataLoadingStarted -> oldState.copy(isLoading = true)

        is ShowDataRequested -> oldState.copy(
            isLoading = false,
            teamDto = action.team
        )

        else -> super.reduce(oldState, action)
    }

    override fun publishSubscription(state: TeamEditState, action: TeamEditAction) = when (action) {
        is TeamSavingSucceed -> TeamEditSubscription.TeamSavingSucceed(action.teamId)
        is TeamSavingFailed -> TeamEditSubscription.TeamSavingFailed(action.error)
        else -> super.publishSubscription(state, action)
    }


    private companion object {

        private fun TeamEditState.toShowDataAction(
            team: Team = teamDto.team,
            participants: List<Munchkin> = teamDto.participants
        ) = ShowDataRequested(teamDto.copy(team = team, participants = participants)).toObservable()

    }

}