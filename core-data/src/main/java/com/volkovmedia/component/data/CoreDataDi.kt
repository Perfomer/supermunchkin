package com.volkovmedia.component.data

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val coreDataModule = module {
    single { MunchkinDatabase.getInstance(androidContext(), "db") }

    single { get<MunchkinDatabase>().getMunchkinDao() }
    single { get<MunchkinDatabase>().getTeamDao() }
}