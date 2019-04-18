package com.volkovmedia.feature.teamlist.presentation

import com.volkovmedia.commons.mvi.MviViewModel
import com.volkovmedia.feature.teamlist.domain.TeamListInteractor
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListAction
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListState

internal class TeamListViewModel(
    private val interactor: TeamListInteractor
) : MviViewModel<TeamListIntent, TeamListAction, TeamListState, Nothing>(
    initialState = TeamListState()
) {

    override fun act(state: TeamListState, intent: TeamListIntent) = when (intent) {
        TeamListIntent.LoadData -> interactor.getTeams()
            .map<TeamListAction> { TeamListAction.DataShowRequested(it) }
            .startWith(TeamListAction.DataLoadingStarted)

        is TeamListIntent.CreateTeam -> interactor.createTeam(intent.name)
            .andThen(super.act(state, intent))

        is TeamListIntent.UpdateTeam -> interactor.updateTeam(intent.team)
            .andThen(super.act(state, intent))

        is TeamListIntent.RemoveTeam -> interactor.removeTeam(intent.team)
            .andThen(super.act(state, intent))
    }

    override fun reduce(oldState: TeamListState, action: TeamListAction) = when (action) {
        TeamListAction.DataLoadingStarted -> oldState.copy(
            isLoading = true
        )

        is TeamListAction.DataShowRequested -> oldState.copy(
            isLoading = false,
            payload = action.payload
        )
    }

}