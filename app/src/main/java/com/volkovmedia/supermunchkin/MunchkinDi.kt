package com.volkovmedia.supermunchkin

import com.volkovmedia.coredata.coreDataModule
import com.volkovmedia.feature.teamslist.teamsListModule
import org.koin.dsl.module.module

val appModule = module {

}

val koinModules = listOf(
    appModule,
    teamsListModule,
    coreDataModule
)