package com.volkovmedia.feature.teamslist.presentation.mvi

import com.volkovmedia.coredata.model.dto.TeamDto

sealed class TeamsListAction {

    object DataLoadingStarted: TeamsListAction()

    class DataShowRequested(val payload: List<TeamDto>): TeamsListAction()

}