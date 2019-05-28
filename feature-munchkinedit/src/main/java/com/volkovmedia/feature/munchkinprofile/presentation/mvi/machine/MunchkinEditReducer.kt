package com.volkovmedia.feature.munchkinprofile.presentation.mvi.machine

import com.volkovmedia.component.common.mvi.machine.MviReducer
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditAction
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditState

internal class MunchkinEditReducer : MviReducer<MunchkinEditState, MunchkinEditAction>() {

    override fun reduce(oldState: MunchkinEditState, action: MunchkinEditAction) = when (action) {
        MunchkinEditAction.DataLoadingStarted -> oldState.copy(isLoading = true)
        is MunchkinEditAction.ShowDataRequested -> oldState.copy(isLoading = false, munchkin = action.munchkin)
        else -> super.reduce(oldState, action)
    }

}