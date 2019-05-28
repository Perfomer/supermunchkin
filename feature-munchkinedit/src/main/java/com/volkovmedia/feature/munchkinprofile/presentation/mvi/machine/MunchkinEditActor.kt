package com.volkovmedia.feature.munchkinprofile.presentation.mvi.machine

import com.volkovmedia.component.common.mvi.machine.MviActor
import com.volkovmedia.component.common.util.toObservable
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.feature.munchkinprofile.domain.MunchkinEditInteractor
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditAction
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditIntent
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditIntent.*
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditState
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

        is EditLevel -> state.toShowDataAction(level = intent.level)

        is EditGear -> state.toShowDataAction(gear = intent.gear)

        KillMunchkin -> state.toShowDataAction(gear = 0)

        AcceptChanges -> interactor.saveMunchkin(state.munchkin)
            .andThen(MunchkinEditAction.DataSaved.toObservable())
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