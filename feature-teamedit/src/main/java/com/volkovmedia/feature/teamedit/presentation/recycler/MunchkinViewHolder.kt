package com.volkovmedia.feature.teamedit.presentation.recycler

import android.view.View
import com.volkovmedia.component.common.util.getColorCompat
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.common.util.setBackgroundTint
import com.volkovmedia.component.common.util.setTint
import com.volkovmedia.component.common.view.recycler.base.BindableViewHolder
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.component.resource.colorDarkResource
import com.volkovmedia.component.resource.colorLightResource
import com.volkovmedia.component.resource.colorResource
import com.volkovmedia.component.resource.drawableResource
import kotlinx.android.synthetic.main.teamedit_item.*

internal class MunchkinViewHolder(
    containerView: View,
    onClick: (Int) -> Unit,
    onDeleteClick: (Int) -> Unit
) : BindableViewHolder<Munchkin>(containerView) {

    init {
        teamedit_item_name.onClick = { if (hasPosition) onClick.invoke(adapterPosition) }
        teamedit_item_delete.onClick = { if (hasPosition) onDeleteClick.invoke(adapterPosition) }
    }

    override fun bind(item: Munchkin) {
        val gender = item.gender

        teamedit_item_name.text = item.name
        teamedit_item_name.setTextColor(resources.getColorCompat(gender.colorDarkResource))
        teamedit_item_name.setBackgroundTint(gender.colorLightResource)

        teamedit_item_icon.setImageResource(gender.drawableResource)
        teamedit_item_icon.setBackgroundTint(gender.colorResource)

        teamedit_item_delete.setTint(gender.colorResource)
    }

}