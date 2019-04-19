package com.volkovmedia.component.common.view.recycler

import androidx.recyclerview.widget.DiffUtil

open class EntityDiffCallback<Item, PrimaryKey>(
        private val oldItems: List<Item>,
        private val newItems: List<Item>,
        private val identifierReceiver: (Item) -> PrimaryKey
): DiffUtil.Callback() {

    override fun getOldListSize() = oldItems.size

    override fun getNewListSize() = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldItem = oldItems[oldItemPosition]
        val newItem = newItems[newItemPosition]

        return identifierReceiver.invoke(oldItem) == identifierReceiver.invoke(newItem)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

}