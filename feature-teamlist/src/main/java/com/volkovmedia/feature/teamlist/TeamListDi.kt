package com.volkovmedia.feature.teamlist

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.teamlist.data.TeamListDataSource
import com.volkovmedia.feature.teamlist.domain.TeamListInteractor
import com.volkovmedia.feature.teamlist.domain.TeamListRepository
import com.volkovmedia.feature.teamlist.presentation.TeamListFragment
import com.volkovmedia.feature.teamlist.presentation.TeamListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val teamListModule = module {
    single { TeamListDataSource(get()) } bind TeamListRepository::class
    single { TeamListInteractor(get()) }
    viewModel { TeamListViewModel(get()) }
    factory(name = FRAGMENT_TEAMLIST) { TeamListFragment() } bind Fragment::class
}

const val FRAGMENT_TEAMLIST = "TeamListFragment"