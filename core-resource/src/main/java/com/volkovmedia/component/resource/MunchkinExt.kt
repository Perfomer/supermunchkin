package com.volkovmedia.component.resource

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.MunchkinGender.*

val MunchkinGender.drawableResource: Int
    @DrawableRes get() = when (this) {
        NONE -> R.drawable.ic_none
        MALE -> R.drawable.ic_male
        FEMALE -> R.drawable.ic_female
    }

val MunchkinGender.colorResource: Int
    @ColorRes get() = when (this) {
        NONE -> R.color.grey_medium
        MALE -> R.color.blue
        FEMALE -> R.color.pink
    }

val MunchkinGender.colorLightResource: Int
    @ColorRes get() = when (this) {
        NONE -> R.color.grey_light
        MALE -> R.color.blue_light
        FEMALE -> R.color.pink_light
    }

val MunchkinGender.colorDarkResource: Int
    @ColorRes get() = when (this) {
        NONE -> R.color.grey_dark
        MALE -> R.color.blue_dark
        FEMALE -> R.color.pink
    }