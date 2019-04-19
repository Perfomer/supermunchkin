package com.volkovmedia.component.common.view.recycler

import com.volkovmedia.component.common.KeyEntity
import com.volkovmedia.component.common.view.recycler.base.BindableViewHolder

abstract class BindableEntityAdapter<T : KeyEntity<*>, VH : BindableViewHolder<T>> : EntityAdapter<T, VH>() {

    override fun onBindViewHolder(holder: VH, item: T) = holder.bind(item)

}