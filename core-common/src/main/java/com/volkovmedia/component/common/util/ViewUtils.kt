package com.volkovmedia.component.common.util

import android.content.Context
import androidx.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment

var View.onClick: () -> Unit
    get() = {}
    set(value) = setOnClickListener { value() }

var View.onLongClick: () -> Unit
    get() = {}
    set(value) = setOnLongClickListener {
        value()
        true
    }

fun Fragment.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View? {
    return context?.inflate(resource, root, attachToRoot)
}

fun Context.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    val inflater = LayoutInflater.from(this)
    return inflater.inflate(resource, root, attachToRoot)
}

fun View.inflate(@LayoutRes resource: Int, root: ViewGroup? = null, attachToRoot: Boolean = false): View {
    return context.inflate(resource, root, attachToRoot)
}

fun View.setBackgroundTint(@ColorRes colorRes: Int) {
    backgroundTintList = resources.getColorStateList(colorRes)
}

fun ImageView.setTint(@ColorRes colorRes: Int) {
    imageTintList = resources.getColorStateList(colorRes)
}