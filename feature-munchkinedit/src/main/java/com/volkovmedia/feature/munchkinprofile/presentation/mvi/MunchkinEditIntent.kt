package com.volkovmedia.feature.munchkinprofile.presentation.mvi

import com.volkovmedia.component.data.model.MunchkinGender

internal sealed class MunchkinEditIntent {

    object LoadData: MunchkinEditIntent()

    class EditName(val name: String): MunchkinEditIntent()

    class EditGender(val gender: MunchkinGender): MunchkinEditIntent()

    class EditLevel(val level: Int): MunchkinEditIntent()

    class EditGear(val gear: Int): MunchkinEditIntent()

    object KillMunchkin: MunchkinEditIntent()

    object AcceptChanges: MunchkinEditIntent()

}