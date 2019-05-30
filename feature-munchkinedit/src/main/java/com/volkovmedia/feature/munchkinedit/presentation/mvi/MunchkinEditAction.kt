package com.volkovmedia.feature.munchkinedit.presentation.mvi

import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinedit.presentation.MunchkinEditInputError

internal sealed class MunchkinEditAction {

    object DataLoadingStarted : MunchkinEditAction()

    class ShowDataRequested(val munchkin: Munchkin) : MunchkinEditAction()

    class WrongDataInputted(val error: MunchkinEditInputError) : MunchkinEditAction()

    object DataSaved : MunchkinEditAction()

}