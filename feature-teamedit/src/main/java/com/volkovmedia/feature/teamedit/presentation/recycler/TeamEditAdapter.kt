package com.volkovmedia.feature.teamedit.presentation.recycler

import android.view.View
import com.volkovmedia.component.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.teamedit.R

internal class TeamEditAdapter(
    private val onClick: (Munchkin) -> Unit,
    private val onDeleteClick: (Munchkin) -> Unit
) : BindableEntityAdapter<Munchkin, MunchkinViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.teamedit_item

    override fun onCreateViewHolder(view: View, viewType: Int) = MunchkinViewHolder(view, ::onClick, ::onDeleteClick)


    private fun onClick(position: Int) = onClick.invoke(items[position])

    private fun onDeleteClick(position: Int) = onDeleteClick.invoke(items[position])

}