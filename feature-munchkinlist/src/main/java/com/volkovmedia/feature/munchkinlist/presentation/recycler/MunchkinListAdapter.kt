package com.volkovmedia.feature.munchkinlist.presentation.recycler

import android.view.View
import com.volkovmedia.component.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.feature.munchkinlist.R
import com.volkovmedia.feature.munchkinlist.domain.model.MunchkinDto

internal class MunchkinListAdapter(
    private val onClick: (MunchkinDto) -> Unit,
    private val onLevelRaiseUpClick: (MunchkinDto, Boolean) -> Unit,
    private val onGearRaiseUpClick: (MunchkinDto, Boolean) -> Unit
) : BindableEntityAdapter<MunchkinDto, MunchkinViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.munchkinlist_item

    override fun onCreateViewHolder(view: View, viewType: Int) = MunchkinViewHolder(
        view, ::onClick, ::onLevelRaiseUpClick, ::onGearRaiseUpClick
    )


    private fun onClick(position: Int) = onClick.invoke(items[position])

    private fun onLevelRaiseUpClick(position: Int, raiseUp: Boolean) {
        onLevelRaiseUpClick.invoke(items[position], raiseUp)
    }

    private fun onGearRaiseUpClick(position: Int, raiseUp: Boolean) {
        onGearRaiseUpClick.invoke(items[position], raiseUp)
    }

}