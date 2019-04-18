package com.volkovmedia.commons.view.recycler

import com.volkovmedia.commons.KeyEntity
import com.volkovmedia.commons.view.recycler.base.BindableViewHolder

abstract class BindableEntityAdapter<T : KeyEntity<*>, VH : BindableViewHolder<T>> : EntityAdapter<T, VH>() {

    override fun onBindViewHolder(holder: VH, item: T) = holder.bind(item)

}