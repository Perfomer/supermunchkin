package com.volkovmedia.feature.teamlist.presentation.recycler

import android.view.View
import com.volkovmedia.component.common.view.recycler.BindableEntityAdapter
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.feature.teamlist.R

internal class TeamListAdapter(
    private val onClick: (TeamDto) -> Unit,
    private val onLongClick: (TeamDto) -> Unit
) : BindableEntityAdapter<TeamDto, TeamViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.teamslist_item

    override fun onCreateViewHolder(view: View, viewType: Int) = TeamViewHolder(view, ::onClick, ::onLongClick)


    private fun onClick(position: Int) = onClick.invoke(items[position])

    private fun onLongClick(position: Int) = onLongClick.invoke(items[position])

}