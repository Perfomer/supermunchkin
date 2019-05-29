package com.volkovmedia.feature.munchkinedit.presentation

import androidx.annotation.StringRes
import com.volkovmedia.feature.munchkinedit.R

internal enum class MunchkinEditInputError(
    @StringRes val messageResource: Int
) {

    EMPTY_NAME(R.string.munchkinedit_inputerror_emptyname)

}