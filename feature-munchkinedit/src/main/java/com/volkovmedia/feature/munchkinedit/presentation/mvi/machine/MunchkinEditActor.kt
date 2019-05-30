package com.volkovmedia.feature.munchkinedit.presentation.mvi.machine

import com.volkovmedia.component.common.mvi.machine.MviActor
import com.volkovmedia.component.common.util.toObservable
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.feature.munchkinedit.domain.MunchkinEditInteractor
import com.volkovmedia.feature.munchkinedit.presentation.MunchkinEditInputError.EMPTY_NAME
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditAction
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditIntent
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditIntent.*
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditState
import io.reactivex.Observable

internal class MunchkinEditActor(
    private val munchkinId: Long,
    private val interactor: MunchkinEditInteractor
) : MviActor<MunchkinEditState, MunchkinEditIntent, MunchkinEditAction>() {

    override fun act(state: MunchkinEditState, intent: MunchkinEditIntent) = when (intent) {
        LoadData -> interactor.getMunchkin(munchkinId)
            .toObservable()
            .map<MunchkinEditAction> { MunchkinEditAction.ShowDataRequested(it) }
            .startWith(MunchkinEditAction.DataLoadingStarted)

        is EditName -> state.toShowDataAction(name = intent.name)

        is EditGender -> state.toShowDataAction(gender = intent.gender)

        IncreaseLevel -> state.toShowDataAction(level = state.munchkin.level + 1)

        DecreaseLevel -> state.toShowDataAction(level = state.munchkin.level - 1)

        IncreaseGear -> state.toShowDataAction(level = state.munchkin.gear + 1)

        DecreaseGear -> state.toShowDataAction(level = state.munchkin.gear - 1)

        KillMunchkin -> state.toShowDataAction(gear = 0)

        AcceptChanges -> {
            if (state.munchkin.name.isBlank()) {
                Observable.just(MunchkinEditAction.WrongDataInputted(EMPTY_NAME))
            } else {
                interactor.saveMunchkin(state.munchkin)
                    .andThen(MunchkinEditAction.DataSaved.toObservable())
            }
        }
    }

    private companion object {

        private fun MunchkinEditState.toShowDataAction(
            name: String = munchkin.name,
            level: Int = munchkin.level,
            gear: Int = munchkin.gear,
            gender: MunchkinGender = munchkin.gender
        ): Observable<MunchkinEditAction> {
            val updatedMunchkin = munchkin.copy(
                name = name,
                level = level,
                gear = gear,
                gender = gender
            )

            return MunchkinEditAction.ShowDataRequested(updatedMunchkin).toObservable()
        }

    }

}