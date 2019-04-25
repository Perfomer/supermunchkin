package com.volkovmedia.feature.munchkinprofile.presentation.mvi

internal sealed class MunchkinEditSubscription {

    object MunchkinChangedSuccessful: MunchkinEditSubscription()

    class WrongDataInputted(val error: Throwable): MunchkinEditSubscription()

}