package com.volkovmedia.commons.view.recycler.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.volkovmedia.commons.util.onClick
import kotlinx.android.extensions.LayoutContainer

abstract class BaseViewHolder(
    override val containerView: View
) : RecyclerView.ViewHolder(containerView), LayoutContainer {

    protected val context by lazy { itemView.context!! }

    protected val resources by lazy { itemView.resources!! }

    protected val hasPosition: Boolean
        get() = adapterPosition != NO_POSITION

    protected fun View.setSafeOnClickListener(listener: (Int) -> Unit) {
        onClick = { if (hasPosition) listener.invoke(adapterPosition) }
    }

}