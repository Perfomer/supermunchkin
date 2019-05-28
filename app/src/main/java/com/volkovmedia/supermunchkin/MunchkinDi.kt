package com.volkovmedia.supermunchkin

import com.volkovmedia.component.data.databaseModule
import com.volkovmedia.feature.munchkinlist.munchkinListModule
import com.volkovmedia.feature.munchkinprofile.munchkinEditModule
import com.volkovmedia.feature.teamedit.teamEditModule
import com.volkovmedia.feature.teamlist.teamListModule
import com.volkovmedia.supermunchkin.navigation.navigationModule

val koinModules = listOf(
    navigationModule,

    databaseModule,

    teamEditModule,
    teamListModule,
    munchkinEditModule,
    munchkinListModule
)