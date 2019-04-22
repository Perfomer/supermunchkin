package com.volkovmedia.feature.munchkinlist.presentation

interface MunchkinListNavigator {

    fun navigateToBattle()

    fun navigateToTeamEdit(teamId: Long)

    fun navigateToMunchkinEdit(munchkinId: Long)

}