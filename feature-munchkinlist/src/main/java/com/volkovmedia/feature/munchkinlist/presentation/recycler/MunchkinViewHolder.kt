package com.volkovmedia.feature.munchkinlist.presentation.recycler

import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.component.common.util.getColorCompat
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.common.util.onLongClick
import com.volkovmedia.component.common.util.setBackgroundTint
import com.volkovmedia.component.common.view.recycler.base.BindableViewHolder
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.component.resource.colorDarkResource
import com.volkovmedia.component.resource.colorResource
import com.volkovmedia.component.resource.drawableResource
import com.volkovmedia.component.resource.stringResource
import com.volkovmedia.feature.munchkinlist.R
import com.volkovmedia.feature.munchkinlist.domain.model.MunchkinDto
import kotlinx.android.synthetic.main.munchkinlist_item.*

internal class MunchkinViewHolder(
    itemView: View,
    onClick: (Int) -> Unit,
    onLevelRaisedUp: (Int, Boolean) -> Unit,
    onGearRaisedUp: (Int, Boolean) -> Unit
) : BindableViewHolder<MunchkinDto>(itemView) {

    init {
        munchkinlist_item_card.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
//        munchkinlist_item_card.onLongClick = { if (hasPosition) onLongClick.invoke(adapterPosition) }

        munchkinlist_item_level_minus.onClick = { if (hasPosition) onLevelRaisedUp.invoke(adapterPosition, false) }
        munchkinlist_item_level_plus.onClick = { if (hasPosition) onLevelRaisedUp.invoke(adapterPosition, true) }

        munchkinlist_item_gear_minus.onClick = { if (hasPosition) onGearRaisedUp.invoke(adapterPosition, false) }
        munchkinlist_item_gear_plus.onClick = { if (hasPosition) onGearRaisedUp.invoke(adapterPosition, true) }
    }

    override fun bind(item: MunchkinDto) {
        val munchkin = item.munchkin
        val gender = munchkin.gender
        val isLevelDownAvailable = munchkin.level > 1

        munchkinlist_item_name.text = munchkin.name
        munchkinlist_item_name.setTextColor(resources.getColorCompat(gender.colorDarkResource))

        munchkinlist_item_gender.setImageResource(gender.drawableResource)
        munchkinlist_item_gender.setBackgroundTint(gender.colorResource)

        munchkinlist_item_level_value.text = munchkin.level.toString()
        munchkinlist_item_gear_value.text = munchkin.gear.toString()
        munchkinlist_item_strength_title.text = munchkin.strength.toString()

        munchkinlist_item_level_minus.isEnabled = isLevelDownAvailable
        munchkinlist_item_level_minus.setBackgroundTint(
            if (isLevelDownAvailable) R.color.accent
            else R.color.grey_medium
        )

        munchkinlist_item_race.text = munchkin.getRaceString()
        munchkinlist_item_class.text = munchkin.getClassString()

        munchkinlist_item_class.isVisible = munchkin.getClassString().isNotBlank()
    }

    private fun Munchkin.getClassString() = listOfNotNull(firstClass, secondClass)
        .joinToString(LIST_SEPARATOR) { getString(it.stringResource) }

    private fun Munchkin.getRaceString() = listOfNotNull(firstRace, secondRace)
        .joinToString(LIST_SEPARATOR) { getString(it.stringResource) }

    private companion object {

        private const val LIST_SEPARATOR = ", "

    }

}