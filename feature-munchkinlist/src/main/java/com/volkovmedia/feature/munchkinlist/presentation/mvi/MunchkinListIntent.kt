package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.component.data.model.entity.Munchkin

internal sealed class MunchkinListIntent {

    object LoadData : MunchkinListIntent()

    class UpdateMunchkin(val munchkin: Munchkin) : MunchkinListIntent()

    class RemoveMunchkin(val munchkin: Munchkin) : MunchkinListIntent()

}