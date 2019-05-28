package com.volkovmedia.supermunchkin.navigation

import androidx.fragment.app.Fragment
import com.volkovmedia.feature.munchkinlist.DI_FRAGMENT_MUNCHKINLIST
import com.volkovmedia.feature.munchkinprofile.DI_FRAGMENT_MUNCHKINEDIT
import com.volkovmedia.feature.teamedit.DI_FRAGMENT_TEAMEDIT
import com.volkovmedia.feature.teamlist.DI_FRAGMENT_TEAMLIST
import org.koin.core.parameter.parametersOf
import org.koin.standalone.KoinComponent
import org.koin.standalone.get
import ru.terrakok.cicerone.android.support.SupportAppScreen

sealed class ScreenDestination : SupportAppScreen(), KoinComponent {

    object TeamList : ScreenDestination() {
        override fun getFragment() = get<Fragment>(DI_FRAGMENT_TEAMLIST)
    }

    class TeamEdit(private val teamId: Long? = null) : ScreenDestination() {
        override fun getFragment() = get<Fragment>(DI_FRAGMENT_TEAMEDIT) { parametersOf(teamId) }
    }

    class MunchkinList(private val teamId: Long) : ScreenDestination() {
        override fun getFragment() = get<Fragment>(DI_FRAGMENT_MUNCHKINLIST) { parametersOf(teamId) }
    }

    class MunchkinEdit(private val munchkinId: Long? = null) : ScreenDestination() {
        override fun getFragment() = get<Fragment>(DI_FRAGMENT_MUNCHKINEDIT) { parametersOf(munchkinId) }
    }

    object Battle : ScreenDestination() {
        override fun getFragment() = TODO()
    }

}