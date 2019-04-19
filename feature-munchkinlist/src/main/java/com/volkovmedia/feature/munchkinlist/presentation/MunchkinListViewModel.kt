package com.volkovmedia.feature.munchkinlist.presentation

import com.volkovmedia.commons.mvi.MviViewModel
import com.volkovmedia.feature.munchkinlist.domain.MunchkinListInteractor
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListAction
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListIntent
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListState

internal class MunchkinListViewModel(
    private val teamId: Long,
    private val interactor: MunchkinListInteractor
) : MviViewModel<MunchkinListIntent, MunchkinListAction, MunchkinListState, Nothing>(
    initialState = MunchkinListState()
) {

    override fun act(state: MunchkinListState, intent: MunchkinListIntent) = when (intent) {
        MunchkinListIntent.LoadData -> interactor.getMunchkins(teamId)
            .map<MunchkinListAction> { MunchkinListAction.DataShowRequested(it) }
            .startWith(MunchkinListAction.DataLoadingStarted)

        is MunchkinListIntent.CreateMunchkin -> interactor.createMunchkin(
            teamId = teamId,
            name = intent.name,
            gender = intent.gender
        ).andThen(super.act(state, intent))

        is MunchkinListIntent.UpdateMunchkin -> interactor.putMunchkin(intent.munchkin)
            .andThen(super.act(state, intent))

        is MunchkinListIntent.RemoveMunchkin -> interactor.removeMunchkin(intent.munchkin)
            .andThen(super.act(state, intent))
    }

    override fun reduce(oldState: MunchkinListState, action: MunchkinListAction) = when(action) {
        MunchkinListAction.DataLoadingStarted -> oldState.copy(
            isLoading = true
        )

        is MunchkinListAction.DataShowRequested -> oldState.copy(
            isLoading = false, payload = action.payload
        )
    }

}