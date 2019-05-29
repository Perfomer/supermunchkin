package com.volkovmedia.feature.munchkinedit.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.jakewharton.rxbinding3.widget.textChanges
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.argumentLong
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.common.util.toast
import com.volkovmedia.feature.munchkinedit.R
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditIntent
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditIntent.*
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditState
import com.volkovmedia.feature.munchkinedit.presentation.mvi.MunchkinEditSubscription
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.munchkinedit_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

internal class MunchkinEditFragment : MviFragment<MunchkinEditIntent, MunchkinEditState, MunchkinEditSubscription>(
    layoutResource = R.layout.munchkinedit_fragment,
    menuResource = R.menu.munchkinedit_menu
) {

    private val munchkinId by argumentLong(KEY_MUNCHKINID)


    override fun provideViewModel() = getViewModel<MunchkinEditViewModel> { parametersOf(munchkinId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        munchkinedit_toolbar.attachToActivity()

        disposable += munchkinedit_name.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .filter { it != currentState?.munchkin?.name }
            .subscribeBy { postIntent(EditName(it.toString())) }

        munchkinedit_gear_plus.onClick = { postIntent(IncreaseGear) }
        munchkinedit_gear_minus.onClick = { postIntent(DecreaseGear) }
        munchkinedit_level_plus.onClick = { postIntent(IncreaseLevel) }
        munchkinedit_level_minus.onClick = { postIntent(DecreaseLevel) }
    }

    override fun render(state: MunchkinEditState) {
        val munchkin = state.munchkin

        munchkinedit_progressbar.isVisible = state.isLoading
        munchkinedit_name.setText(munchkin.name)
        munchkinedit_strength_value.text = munchkin.strength.toString()
        munchkinedit_gear_value.text = munchkin.gear.toString()
        munchkinedit_level_value.text = munchkin.level.toString()

        munchkinedit_level_minus.isEnabled = munchkin.level > 1
    }

    override fun onSubscriptionReceived(subscription: MunchkinEditSubscription) = when (subscription) {
        MunchkinEditSubscription.MunchkinChangedSuccessful -> goBack()
        is MunchkinEditSubscription.WrongDataInputted -> toast(subscription.error.messageResource)
    }


    internal companion object {

        private const val KEY_MUNCHKINID = "munchkinId"

        internal fun newInstance(munchkinId: Long) = MunchkinEditFragment().withArguments {
            putLong(KEY_MUNCHKINID, munchkinId)
        }

    }

}