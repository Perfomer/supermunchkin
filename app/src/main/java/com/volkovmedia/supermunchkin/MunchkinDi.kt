package com.volkovmedia.supermunchkin

import com.volkovmedia.component.coredata.coreDataModule
import com.volkovmedia.feature.munchkinlist.munchkinListModule
import com.volkovmedia.feature.teamlist.teamListModule
import org.koin.dsl.module.module

val appModule = module {

}

val koinModules = listOf(
    appModule,
    teamListModule,
    munchkinListModule,
    coreDataModule
)