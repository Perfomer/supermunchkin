package com.volkovmedia.feature.munchkinedit.presentation.mvi

import com.volkovmedia.component.data.model.MunchkinGender

internal sealed class MunchkinEditIntent {

    object LoadData: MunchkinEditIntent()

    class EditName(val name: String): MunchkinEditIntent()

    class EditGender(val gender: MunchkinGender): MunchkinEditIntent()

    object IncreaseLevel : MunchkinEditIntent()

    object DecreaseLevel : MunchkinEditIntent()

    object IncreaseGear : MunchkinEditIntent()

    object DecreaseGear : MunchkinEditIntent()

    object KillMunchkin: MunchkinEditIntent()

    object AcceptChanges: MunchkinEditIntent()

}