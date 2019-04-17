package com.volkovmedia.feature.teamslist.presentation.recycler

import android.view.View
import com.volkovmedia.commons.util.format
import com.volkovmedia.commons.util.onClick
import com.volkovmedia.commons.util.onLongClick
import com.volkovmedia.commons.view.recycler.base.BaseViewHolder
import com.volkovmedia.coredata.model.entity.Team
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.teamslist_item.*
import kotlinx.android.synthetic.main.teamslist_item.view.*

class TeamViewHolder(
    override val containerView: View,
    private val onClick: (Int) -> Unit,
    private val onLongClick: (Int) -> Unit
) : BaseViewHolder(containerView), LayoutContainer {

    init {
        teamslist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
        teamslist_item_card.onLongClick = { if (hasPosition) onLongClick.invoke(adapterPosition) }
    }

    fun bind(item: Team) {
        teamslist_item_name.text = item.name
        teamslist_item_id.text = item.id.toString()
        teamslist_item_participants.text = "stub"
        teamslist_item_date.text = item.lastGameDate.format()
    }

}