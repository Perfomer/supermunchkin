package com.volkovmedia.feature.teamlist.presentation.recycler

import android.view.View
import com.volkovmedia.component.common.util.*
import com.volkovmedia.component.common.view.recycler.base.BindableViewHolder
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.teamlist.R
import kotlinx.android.synthetic.main.teamslist_item.*

internal class TeamViewHolder(
    itemView: View,
    private val onClick: (Int) -> Unit,
    private val onLongClick: (Int) -> Unit
) : BindableViewHolder<TeamDto>(itemView) {

    init {
        teamslist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
        teamslist_item_card.onLongClick = { if (hasPosition) onLongClick.invoke(adapterPosition) }
    }

    override fun bind(item: TeamDto) {
        val team = item.team
        val isGameFinished = team.isGameFinished
        val hasParticipants = item.participants.isNotEmpty()

        teamslist_item_name.text = team.name
        teamslist_item_id.text = team.id.toString()
        teamslist_item_date.text = team.lastGameDate.format()
        teamslist_item_participants.text = item.participants.getListString()

        teamslist_item_participants_icon.setTint(
            if (hasParticipants) R.color.primary
            else R.color.grey_dark
        )

        teamslist_item_participants.setBackgroundTint(
            if (hasParticipants) R.color.blue_light
            else R.color.grey_light
        )

        teamslist_item_participants.setTextColor(
            resources.getColorCompat(
                if (hasParticipants) R.color.primary_dark
                else R.color.text_secondary
            )
        )

        teamslist_item_status.text = getString(
            if (isGameFinished) R.string.teamlist_game_finished
            else R.string.teamlist_game_inprogress
        )

        teamslist_item_status.setTextColor(
            resources.getColorCompat(
                if (isGameFinished) R.color.text_secondary
                else R.color.primary_dark
            )
        )
    }

    private fun List<Munchkin>.getListString(): String {
        return if (isEmpty()) return getString(R.string.teamlist_noparticipants)
        else joinToString(separator = ", ") { it.name }
    }

}