package com.volkovmedia.feature.munchkinedit.presentation.mvi

import com.volkovmedia.feature.munchkinedit.presentation.MunchkinEditInputError

internal sealed class MunchkinEditSubscription {

    object MunchkinChangedSuccessful: MunchkinEditSubscription()

    class WrongDataInputted(val error: MunchkinEditInputError): MunchkinEditSubscription()

}