package com.volkovmedia.commons.view.recycler

import androidx.recyclerview.widget.DiffUtil
import com.volkovmedia.commons.KeyEntity
import com.volkovmedia.commons.view.recycler.base.BaseAdapter
import com.volkovmedia.commons.view.recycler.base.BaseViewHolder

abstract class EntityAdapter<T : KeyEntity<*>, VH : BaseViewHolder> : BaseAdapter<VH>() {

    var items: List<T> = listOf()
        set(value) {
            val callback = EntityDiffCallback(field, value) { it.id }
            val result = DiffUtil.calculateDiff(callback)

            field = value

            result.dispatchUpdatesTo(this)
        }

    override fun getItemCount() = items.size

    override fun getItemId(position: Int) = items[position].id.hashCode().toLong()

    override fun onBindViewHolder(holder: VH, position: Int) = onBindViewHolder(holder, items[position])

    abstract fun onBindViewHolder(holder: VH, item: T)

}