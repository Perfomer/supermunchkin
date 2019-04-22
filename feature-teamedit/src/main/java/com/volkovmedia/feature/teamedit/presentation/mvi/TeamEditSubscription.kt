package com.volkovmedia.feature.teamedit.presentation.mvi

internal sealed class TeamEditSubscription {

    class TeamSavingSucceed(val teamId: Long): TeamEditSubscription()

    class TeamSavingFailed(val error: Throwable): TeamEditSubscription()

}