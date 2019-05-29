package com.volkovmedia.supermunchkin.presentation.navigation

import androidx.fragment.app.FragmentActivity
import org.koin.dsl.module.module
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

typealias CiceroneRouter = Cicerone<Router>

val navigationModule = module {
    factory { (activity: FragmentActivity, containerId: Int) ->
        SupportAppNavigator(activity, containerId)
    }

    single { Cicerone.create() }

    single { get<CiceroneRouter>().router }
    single { get<CiceroneRouter>().navigatorHolder }
}