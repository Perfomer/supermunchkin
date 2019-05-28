package com.volkovmedia.feature.munchkinedit

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.munchkinedit.data.MunchkinEditDataSource
import com.volkovmedia.feature.munchkinedit.domain.MunchkinEditInteractor
import com.volkovmedia.feature.munchkinedit.domain.MunchkinEditRepository
import com.volkovmedia.feature.munchkinedit.presentation.MunchkinEditFragment
import com.volkovmedia.feature.munchkinedit.presentation.MunchkinEditViewModel
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditActor
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditReducer
import com.volkovmedia.feature.munchkinedit.presentation.mvi.machine.MunchkinEditSubscriptionPublisher
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val munchkinEditModule = module {
    single { MunchkinEditDataSource(get()) } bind MunchkinEditRepository::class
    single { MunchkinEditInteractor(get()) }

    single { (munchkinId: Long) -> MunchkinEditActor(munchkinId, get()) }
    single { MunchkinEditReducer() }
    single { MunchkinEditSubscriptionPublisher() }

    viewModel { (munchkinId: Long) -> MunchkinEditViewModel(munchkinId) }

    factory(name = DI_FRAGMENT_MUNCHKINEDIT) { (munchkinId: Long) ->
        MunchkinEditFragment.newInstance(munchkinId)
    } bind Fragment::class
}

const val DI_FRAGMENT_MUNCHKINEDIT = "MunchkinEditFragment"