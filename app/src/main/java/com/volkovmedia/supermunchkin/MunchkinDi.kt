package com.volkovmedia.supermunchkin

import com.volkovmedia.component.data.coreDataModule
import com.volkovmedia.feature.munchkinlist.munchkinListModule
import com.volkovmedia.feature.munchkinprofile.munchkinEditModule
import com.volkovmedia.feature.teamedit.teamEditModule
import com.volkovmedia.feature.teamlist.teamListModule
import org.koin.dsl.module.module

val appModule = module {

}

val koinModules = listOf(
    appModule,
    coreDataModule,
    teamEditModule,
    teamListModule,
    munchkinEditModule,
    munchkinListModule
)