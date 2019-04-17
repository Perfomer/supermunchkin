package com.volkovmedia.feature.teamlist.presentation.recycler

import android.view.View
import com.volkovmedia.commons.view.recycler.EntityAdapter
import com.volkovmedia.coredata.model.dto.TeamDto
import com.volkovmedia.feature.teamlist.R

internal class TeamListAdapter(
    private val onClick: (TeamDto) -> Unit,
    private val onLongClick: (TeamDto) -> Unit
) : EntityAdapter<TeamDto, TeamViewHolder>() {

    override fun onLayoutRequested(viewType: Int) = R.layout.teamslist_item

    override fun onCreateViewHolder(view: View, viewType: Int) = TeamViewHolder(view, ::onClick, ::onLongClick)

    override fun onBindViewHolder(holder: TeamViewHolder, item: TeamDto) = holder.bind(item)


    private fun onClick(position: Int) = onClick.invoke(items[position])

    private fun onLongClick(position: Int) = onLongClick.invoke(items[position])

}