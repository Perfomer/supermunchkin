package com.volkovmedia.feature.munchkinedit.presentation

import com.volkovmedia.component.common.mvi.MviViewModel
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditAction
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditIntent
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditState
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditSubscription
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditActor
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditReducer
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditSubscriptionPublisher
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.inject

internal class MunchkinEditViewModel(
    private val munchkinId: Long
) : MviViewModel<MunchkinEditIntent, MunchkinEditAction, MunchkinEditState, MunchkinEditSubscription>(
    initialState = MunchkinEditState()
), KoinComponent {

    private val actor: MunchkinEditActor by inject { parametersOf(munchkinId) }

    private val reducer: MunchkinEditReducer by inject()

    private val subscriptionPublisher: MunchkinEditSubscriptionPublisher by inject()


    init {
        postIntent(MunchkinEditIntent.LoadData)
    }


    override fun act(state: MunchkinEditState, intent: MunchkinEditIntent) = actor.act(state, intent)

    override fun reduce(oldState: MunchkinEditState, action: MunchkinEditAction) = reducer.reduce(oldState, action)

    override fun publishSubscription(state: MunchkinEditState, action: MunchkinEditAction) =
        subscriptionPublisher.publishSubscription(state, action)

}