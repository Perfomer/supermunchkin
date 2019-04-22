package com.volkovmedia.feature.teamedit.presentation.mvi

import com.volkovmedia.component.data.model.dto.TeamDto

internal sealed class TeamEditAction {

    object DataLoadingStarted: TeamEditAction()

    class ShowDataRequested(val team: TeamDto): TeamEditAction()

    class TeamSavingSucceed(val teamId: Long): TeamEditAction()

    class TeamSavingFailed(val error: Throwable): TeamEditAction()

}