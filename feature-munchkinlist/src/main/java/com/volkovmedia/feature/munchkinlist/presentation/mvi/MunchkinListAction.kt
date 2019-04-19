package com.volkovmedia.feature.munchkinlist.presentation.mvi

import com.volkovmedia.feature.munchkinlist.domain.MunchkinDto

internal sealed class MunchkinListAction {

    object DataLoadingStarted: MunchkinListAction()

    class DataShowRequested(val payload: List<MunchkinDto>) : MunchkinListAction()

}