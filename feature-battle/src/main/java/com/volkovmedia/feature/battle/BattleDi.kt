package com.volkovmedia.feature.battle

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.battle.data.BattleDataSource
import com.volkovmedia.feature.battle.domain.BattleInteractor
import com.volkovmedia.feature.battle.domain.BattleRepository
import com.volkovmedia.feature.battle.presentation.BattleFragment
import com.volkovmedia.feature.battle.presentation.BattleViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val battleModule = module {
    single { BattleDataSource(get()) } bind BattleRepository::class
    single { BattleInteractor(get()) }

    viewModel { BattleViewModel(get()) }
    factory(name = DI_FRAGMENT_BATTLE) { BattleFragment() } bind Fragment::class
}

const val DI_FRAGMENT_BATTLE = "BattleFragment"