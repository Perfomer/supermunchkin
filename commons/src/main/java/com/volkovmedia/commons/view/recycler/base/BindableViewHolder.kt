package com.volkovmedia.commons.view.recycler.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.NO_POSITION
import com.volkovmedia.commons.util.onClick
import kotlinx.android.extensions.LayoutContainer

abstract class BindableViewHolder<T>(
    containerView: View
) : BaseViewHolder(containerView) {

    abstract fun bind(item: T)

}