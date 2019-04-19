package com.volkovmedia.component.resource

import androidx.annotation.StringRes
import com.volkovmedia.component.data.model.MunchkinClass
import com.volkovmedia.component.data.model.MunchkinClass.*
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