package com.volkovmedia.feature.battle.presentation

import com.volkovmedia.component.common.mvi.MviViewModel
import com.volkovmedia.feature.battle.domain.BattleInteractor
import com.volkovmedia.feature.battle.presentation.mvi.BattleAction
import com.volkovmedia.feature.battle.presentation.mvi.BattleIntent
import com.volkovmedia.feature.battle.presentation.mvi.BattleState
import com.volkovmedia.feature.battle.presentation.mvi.BattleSubscription
import io.reactivex.Observable

internal class BattleViewModel(
    private val interactor: BattleInteractor
): MviViewModel<BattleIntent, BattleAction, BattleState, BattleSubscription>(
    initialState = BattleState()
) {

    override fun act(state: BattleState, intent: BattleIntent): Observable<out BattleAction> {
        return super.act(state, intent)
    }

    override fun reduce(oldState: BattleState, action: BattleAction): BattleState {
        return super.reduce(oldState, action)
    }

    override fun publishSubscription(state: BattleState, action: BattleAction): BattleSubscription? {
        return super.publishSubscription(state, action)
    }

}