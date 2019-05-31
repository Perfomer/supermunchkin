package com.volkovmedia.feature.battle.presentation.recycler.monster

import android.view.View
import com.volkovmedia.component.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.feature.battle.R
import com.volkovmedia.feature.battle.domain.model.Monster

internal class MonsterAdapter : BindableEntityAdapter<Monster, MonsterViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.battle_item_participant

    override fun onCreateViewHolder(view: View, viewType: Int) =
        MonsterViewHolder(view)

}