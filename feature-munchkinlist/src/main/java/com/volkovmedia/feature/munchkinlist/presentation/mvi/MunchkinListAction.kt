package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.feature.munchkinlist.domain.model.LocalTeamDto

internal sealed class MunchkinListAction {

    object DataLoadingStarted: MunchkinListAction()

    class DataShowRequested(val teamDto: LocalTeamDto) : MunchkinListAction()

}