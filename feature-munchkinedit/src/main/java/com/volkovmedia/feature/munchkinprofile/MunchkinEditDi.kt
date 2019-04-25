package com.volkovmedia.feature.munchkinprofile

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.munchkinprofile.data.MunchkinEditDataSource
import com.volkovmedia.feature.munchkinprofile.domain.MunchkinEditInteractor
import com.volkovmedia.feature.munchkinprofile.domain.MunchkinEditRepository
import com.volkovmedia.feature.munchkinprofile.presentation.MunchkinEditFragment
import com.volkovmedia.feature.munchkinprofile.presentation.MunchkinEditViewModel
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.machine.MunchkinEditActor
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.machine.MunchkinEditReducer
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.machine.MunchkinEditSubscriptionPublisher
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val munchkinEditModule = module {
    single { MunchkinEditDataSource(get()) } bind MunchkinEditRepository::class
    single { MunchkinEditInteractor(get()) }

    single { (munchkinId: Long) -> MunchkinEditActor(munchkinId, get()) }
    single { MunchkinEditReducer() }
    single { MunchkinEditSubscriptionPublisher() }

    viewModel { (munchkinId: Long) -> MunchkinEditViewModel(munchkinId) }

    factory(name = FRAGMENT_MUNCHKINEDIT) { (munchkinId: Long) ->
        MunchkinEditFragment.newInstance(munchkinId)
    } bind Fragment::class
}

const val FRAGMENT_MUNCHKINEDIT = "MunchkinEditFragment"