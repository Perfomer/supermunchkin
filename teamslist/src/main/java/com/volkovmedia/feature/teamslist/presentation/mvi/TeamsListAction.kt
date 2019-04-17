package com.volkovmedia.feature.teamslist.presentation.mvi

import com.volkovmedia.coredata.model.entity.Team

sealed class TeamsListAction {

    object DataLoadingStarted: TeamsListAction()

    class DataShowRequested(val payload: List<Team>): TeamsListAction()

}