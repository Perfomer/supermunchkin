package com.volkovmedia.feature.battle.presentation.recycler.munchkin

import android.view.View
import com.volkovmedia.component.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.battle.R

internal class MunchkinAdapter : BindableEntityAdapter<Munchkin, MunchkinViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.battle_item_participant

    override fun onCreateViewHolder(view: View, viewType: Int) =
        MunchkinViewHolder(view)

}