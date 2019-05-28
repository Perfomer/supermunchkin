package com.volkovmedia.feature.teamedit

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.teamedit.data.TeamEditDataSource
import com.volkovmedia.feature.teamedit.domain.TeamEditInteractor
import com.volkovmedia.feature.teamedit.domain.TeamEditRepository
import com.volkovmedia.feature.teamedit.presentation.TeamEditFragment
import com.volkovmedia.feature.teamedit.presentation.TeamEditViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val teamEditModule = module {
    viewModel { (teamId: Long?) -> TeamEditViewModel(teamId, get()) }

    factory(name = DI_FRAGMENT_TEAMEDIT) { (teamId: Long?) ->
        TeamEditFragment.newInstance(teamId)
    } bind Fragment::class

    single { TeamEditInteractor(get()) }
    single { TeamEditDataSource(get(), get()) } bind TeamEditRepository::class
}

const val DI_FRAGMENT_TEAMEDIT = "TeamEditFragment"