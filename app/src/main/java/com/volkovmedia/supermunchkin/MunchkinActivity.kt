package com.volkovmedia.supermunchkin

import com.volkovmedia.feature.munchkinlist.presentation.MunchkinListNavigator
import com.volkovmedia.feature.teamedit.presentation.TeamEditNavigator
import com.volkovmedia.feature.teamlist.presentation.TeamListNavigator
import com.volkovmedia.supermunchkin.navigation.ScreenDestination

class MunchkinActivity : BaseActivity(
    screenLayoutResource = R.layout.activity_munchkin,
    frameLayoutResource = R.id.main_frame,
    startScreen = ScreenDestination.TeamList
), TeamListNavigator, TeamEditNavigator, MunchkinListNavigator {

    override fun navigateToTeam(teamId: Long) = navigateTo(ScreenDestination.MunchkinList(teamId))

    override fun navigateToTeamCreate() = navigateTo(ScreenDestination.TeamEdit())

    override fun navigateToTeamEdit(teamId: Long) = navigateTo(ScreenDestination.TeamEdit(teamId))

    override fun navigateToBattle() = TODO("not implemented")

    override fun navigateToMunchkinEdit(munchkinId: Long) = TODO("not implemented")

}