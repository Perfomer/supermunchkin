package com.volkovmedia.feature.munchkinprofile.presentation

import android.os.Bundle
import android.view.View
import com.jakewharton.rxbinding3.view.clicks
import com.jakewharton.rxbinding3.widget.textChanges
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.argumentLong
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.data.model.MunchkinClass
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.MunchkinRace
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinprofile.R
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditIntent
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditIntent.*
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditState
import com.volkovmedia.feature.munchkinprofile.presentation.mvi.MunchkinEditSubscription
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.munchkinedit_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

internal class MunchkinEditFragment : MviFragment<MunchkinEditIntent, MunchkinEditState, MunchkinEditSubscription>() {

    override val layoutResource = R.layout.munchkinedit_fragment

    override val menuResource = R.menu.munchkinedit_menu

    private val munchkinId by argumentLong(KEY_MUNCHKINID)


    override fun provideViewModel() = getViewModel<MunchkinEditViewModel> { parametersOf(munchkinId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        munchkinedit_toolbar.attachToActivity()

        disposable += munchkinedit_name.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeBy { postIntent(EditName(it.toString())) }

        munchkinedit_race_halfblood.setOnCheckedChangeListener { _, isChecked ->
            postIntent(EnableHalfBlood(isChecked))
        }

        munchkinedit_class_supermunchkin.setOnCheckedChangeListener { _, isChecked ->
            postIntent(EnableSuperMunchkin(isChecked))
        }

        munchkinedit_item_gear_plus.onClick = {
            currentState?.let { postIntent(EditGear(it.munchkin.gear + 1)) }
        }

        munchkinedit_item_gear_minus.onClick = {
            currentState?.let { postIntent(EditGear(it.munchkin.gear - 1)) }
        }

        munchkinedit_item_level_plus.onClick = {
            currentState?.let { postIntent(EditGear(it.munchkin.level + 1)) }
        }

        munchkinedit_item_level_minus.onClick = {
            currentState?.let { postIntent(EditGear(it.munchkin.level - 1)) }
        }
    }

    override fun render(state: MunchkinEditState) {

    }

    override fun onSubscriptionReceived(subscription: MunchkinEditSubscription) {

    }

    internal companion object {

        private const val KEY_MUNCHKINID = "munchkinId"

        internal fun newInstance(munchkinId: Long) = MunchkinEditFragment().withArguments {
            putLong(KEY_MUNCHKINID, munchkinId)
        }

    }

}