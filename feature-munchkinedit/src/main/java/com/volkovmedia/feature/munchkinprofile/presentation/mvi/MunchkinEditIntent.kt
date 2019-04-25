package com.volkovmedia.feature.munchkinprofile.presentation.mvi

import com.volkovmedia.component.data.model.MunchkinClass
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.MunchkinRace

internal sealed class MunchkinEditIntent {

    object LoadData: MunchkinEditIntent()

    class EditName(val name: String): MunchkinEditIntent()

    class EditGender(val gender: MunchkinGender): MunchkinEditIntent()

    class EditLevel(val level: Int): MunchkinEditIntent()

    class EditGear(val gear: Int): MunchkinEditIntent()

    class EditFirstClass(val firstClass: MunchkinClass): MunchkinEditIntent()

    class EditSecondClass(val secondClass: MunchkinClass): MunchkinEditIntent()

    class EnableSuperMunchkin(val enable: Boolean): MunchkinEditIntent()

    class EditFirstRace(val firstRace: MunchkinRace): MunchkinEditIntent()

    class EditSecondRace(val secondRace: MunchkinRace): MunchkinEditIntent()

    class EnableHalfBlood(val enable: Boolean): MunchkinEditIntent()

    object KillMunchkin: MunchkinEditIntent()

    object AcceptChanges: MunchkinEditIntent()

}