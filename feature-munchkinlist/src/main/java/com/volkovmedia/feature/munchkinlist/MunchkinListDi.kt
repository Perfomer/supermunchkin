package com.volkovmedia.feature.munchkinlist

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.munchkinlist.data.MunchkinListDataSource
import com.volkovmedia.feature.munchkinlist.domain.MunchkinListInteractor
import com.volkovmedia.feature.munchkinlist.domain.MunchkinListRepository
import com.volkovmedia.feature.munchkinlist.presentation.MunchkinListFragment
import com.volkovmedia.feature.munchkinlist.presentation.MunchkinListViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val munchkinListModule = module {
    single { MunchkinListDataSource(get()) } bind MunchkinListRepository::class
    single { MunchkinListInteractor(get()) }

    viewModel { (teamId: Long) -> MunchkinListViewModel(teamId, get()) }

    factory(name = FRAGMENT_MUNCHKINLIST) { (teamId: Long) ->
        MunchkinListFragment.newInstance(teamId)
    } bind Fragment::class
}

const val FRAGMENT_MUNCHKINLIST = "MunchkinListFragment"