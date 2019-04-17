package com.volkovmedia.feature.teamslist.presentation

import com.volkovmedia.commons.mvi.MviViewModel
import com.volkovmedia.commons.util.currentDate
import com.volkovmedia.coredata.model.entity.Team
import com.volkovmedia.feature.teamslist.domain.TeamsListInteractor
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListAction
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListIntent
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListState
import io.reactivex.Observable

class TeamsListViewModel(
    private val interactor: TeamsListInteractor
) : MviViewModel<TeamsListIntent, TeamsListAction, TeamsListState, Nothing>(
    initialState = TeamsListState()
) {

    override fun act(state: TeamsListState, intent: TeamsListIntent) = when (intent) {
        TeamsListIntent.LoadData -> interactor.getTeams()
            .map<TeamsListAction> { TeamsListAction.DataShowRequested(it) }
            .startWith(TeamsListAction.DataLoadingStarted)

        is TeamsListIntent.CreateTeam -> Observable.just(Team(name = intent.name, lastGameDate = currentDate))
            .flatMapCompletable { interactor.putTeam(it) }
            .andThen(super.act(state, intent))

        is TeamsListIntent.UpdateTeam -> interactor.putTeam(intent.team)
            .andThen(super.act(state, intent))

        is TeamsListIntent.RemoveTeam -> interactor.removeTeam(intent.team)
            .andThen(super.act(state, intent))
    }

    override fun reduce(oldState: TeamsListState, action: TeamsListAction) = when (action) {
        TeamsListAction.DataLoadingStarted -> oldState.copy(
            isLoading = true
        )

        is TeamsListAction.DataShowRequested -> oldState.copy(
            isLoading = false,
            payload = action.payload
        )
    }

}