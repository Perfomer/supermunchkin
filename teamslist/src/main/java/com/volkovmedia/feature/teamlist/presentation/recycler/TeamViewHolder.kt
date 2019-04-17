package com.volkovmedia.feature.teamlist.presentation.recycler

import android.view.View
import com.volkovmedia.commons.util.format
import com.volkovmedia.commons.util.onClick
import com.volkovmedia.commons.util.onLongClick
import com.volkovmedia.commons.view.recycler.base.BaseViewHolder
import com.volkovmedia.coredata.model.dto.TeamDto
import com.volkovmedia.coredata.model.entity.Munchkin
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.teamslist_item.*

internal class TeamViewHolder(
    override val containerView: View,
    private val onClick: (Int) -> Unit,
    private val onLongClick: (Int) -> Unit
) : BaseViewHolder(containerView), LayoutContainer {

    init {
        teamslist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
        teamslist_item_card.onLongClick = { if (hasPosition) onLongClick.invoke(adapterPosition) }
    }

    fun bind(item: TeamDto) {
        val team = item.team

        teamslist_item_name.text = team.name
        teamslist_item_id.text = team.id.toString()
        teamslist_item_participants.text = item.participants.getListString()
        teamslist_item_date.text = team.lastGameDate.format()
    }

    private fun List<Munchkin>.getListString(): String {
        return if (isEmpty()) return "Участников ещё нет"
        else joinToString(separator = ",") { it.name }
    }

}