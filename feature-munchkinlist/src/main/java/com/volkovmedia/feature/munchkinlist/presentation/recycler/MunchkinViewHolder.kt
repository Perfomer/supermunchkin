package com.volkovmedia.feature.munchkinlist.presentation.recycler

import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.common.view.recycler.base.BindableViewHolder
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.coreresource.stringResource
import com.volkovmedia.feature.munchkinlist.domain.MunchkinDto
import kotlinx.android.synthetic.main.munchkinlist_item.*

internal class MunchkinViewHolder(
    itemView: View,
    onClick: (Int) -> Unit,
    onLevelRaisedUp: (Int, Boolean) -> Unit,
    onGearRaisedUp: (Int, Boolean) -> Unit
) : BindableViewHolder<MunchkinDto>(itemView) {

    init {
        munchkinlist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }

        munchkinlist_item_level_down.onClick = { if (hasPosition) onLevelRaisedUp.invoke(adapterPosition, false) }
        munchkinlist_item_level_up.onClick = { if (hasPosition) onLevelRaisedUp.invoke(adapterPosition, true) }

        munchkinlist_item_gear_down.onClick = { if (hasPosition) onGearRaisedUp.invoke(adapterPosition, false) }
        munchkinlist_item_gear_up.onClick = { if (hasPosition) onGearRaisedUp.invoke(adapterPosition, true) }
    }

    override fun bind(item: MunchkinDto) {
        val munchkin = item.munchkin

        val raceString = munchkin.getRaceString()
        val classString = munchkin.getClassString()

        munchkinlist_item_name.text = munchkin.name
        munchkinlist_item_level.text = munchkin.level.toString()
        munchkinlist_item_gear.text = munchkin.gear.toString()
        munchkinlist_item_race.text = raceString
        munchkinlist_item_class.text = classString

        munchkinlist_item_class.isVisible = classString.isNotBlank()
    }

    private fun Munchkin.getClassString() = listOfNotNull(firstClass, secondClass)
        .joinToString(LIST_SEPARATOR) { getString(it.stringResource) }

    private fun Munchkin.getRaceString() = listOfNotNull(firstRace, secondRace)
        .joinToString(LIST_SEPARATOR) { getString(it.stringResource) }

    private companion object {

        private const val LIST_SEPARATOR = ", "

    }

}