package com.volkovmedia.feature.munchkinedit.presentation.mvi.machine

import com.volkovmedia.component.common.mvi.machine.MviSubscriptionPublisher
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditAction
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditAction.*
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditState
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditSubscription

internal class MunchkinEditSubscriptionPublisher :
    MviSubscriptionPublisher<MunchkinEditState, MunchkinEditAction, MunchkinEditSubscription>() {

    override fun publishSubscription(state: MunchkinEditState, action: MunchkinEditAction) = when (action) {
        is WrongDataInputted -> MunchkinEditSubscription.WrongDataInputted(action.error)
        DataSaved -> MunchkinEditSubscription.MunchkinChangedSuccessful
        else -> super.publishSubscription(state, action)
    }

}