package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.entity.Munchkin

internal sealed class MunchkinListIntent {

    object LoadData : MunchkinListIntent()

    class CreateMunchkin(
        val name: String,
        val gender: MunchkinGender
    ) : MunchkinListIntent()

    class UpdateMunchkin(val munchkin: Munchkin) : MunchkinListIntent()

    class RemoveMunchkin(val munchkin: Munchkin) : MunchkinListIntent()

}