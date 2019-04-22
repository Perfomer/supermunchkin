package com.volkovmedia.component.resource

import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.volkovmedia.component.data.model.MunchkinClass
import com.volkovmedia.component.data.model.MunchkinClass.*
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.MunchkinGender.FEMALE
import com.volkovmedia.component.data.model.MunchkinGender.MALE
import com.volkovmedia.component.data.model.MunchkinRace
import com.volkovmedia.component.data.model.MunchkinRace.*

val MunchkinClass.stringResource: Int
    @StringRes get() = when (this) {
        THIEF -> R.string.munchkin_class_thief
        WARRIOR -> R.string.munchkin_class_warrior
        CLERIC -> R.string.munchkin_class_cleric
        WIZARD -> R.string.munchkin_class_wizard
    }

val MunchkinRace.stringResource: Int
    @StringRes get() = when (this) {
        HUMAN -> R.string.munchkin_race_human
        ORC -> R.string.munchkin_race_orc
        DWARF -> R.string.munchkin_race_dwarf
        ELF -> R.string.munchkin_race_elf
        HALFLING -> R.string.munchkin_race_halfling
    }

val MunchkinGender.drawableResource: Int
    @DrawableRes get() = when (this) {
        MALE -> R.drawable.ic_male
        FEMALE -> R.drawable.ic_female
    }

val MunchkinGender.colorResource: Int
    @ColorRes get() = when (this) {
        MALE -> R.color.blue
        FEMALE -> R.color.pink
    }

val MunchkinGender.colorLightResource: Int
    @ColorRes get() = when (this) {
        MALE -> R.color.blue_light
        FEMALE -> R.color.pink_light
    }

val MunchkinGender.colorDarkResource: Int
    @ColorRes get() = when (this) {
        MALE -> R.color.blue_dark
        FEMALE -> R.color.pink
    }