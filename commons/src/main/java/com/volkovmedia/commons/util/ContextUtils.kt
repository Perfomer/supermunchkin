package com.volkovmedia.commons.util

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment

@ColorInt
fun Resources.getColorCompat(@ColorRes id: Int): Int {
    return ResourcesCompat.getColor(this, id, null)
}

fun Resources.getDrawableCompat(@DrawableRes id: Int): Drawable? {
    return ResourcesCompat.getDrawable(this, id, null)
}

fun Context.toast(@StringRes stringId: Int, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, stringId, duration).show()
}

fun Context.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    Toast.makeText(this, text, duration).show()
}

fun Fragment.toast(@StringRes stringId: Int, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(stringId, duration)
}

fun Fragment.toast(text: String, duration: Int = Toast.LENGTH_LONG) {
    context?.toast(text, duration)
}