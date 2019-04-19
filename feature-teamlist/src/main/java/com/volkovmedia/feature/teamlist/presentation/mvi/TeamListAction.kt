package com.volkovmedia.feature.teamlist.presentation.mvi

import com.volkovmedia.component.data.model.dto.TeamDto

internal sealed class TeamListAction {

    object DataLoadingStarted: TeamListAction()

    class DataShowRequested(val payload: List<TeamDto>): TeamListAction()

}