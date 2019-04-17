package com.volkovmedia.feature.teamslist

import com.volkovmedia.feature.teamslist.domain.TeamsListInteractor
import com.volkovmedia.feature.teamslist.presentation.TeamsListFragment
import com.volkovmedia.feature.teamslist.presentation.TeamsListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val teamsListModule = module {
    single { TeamsListInteractor(get()) }
    viewModel { TeamsListViewModel(get()) }
    factory { TeamsListFragment() }
}