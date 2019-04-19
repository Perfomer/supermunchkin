package com.volkovmedia.feature.munchkinlist.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.argumentLong
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinlist.R
import com.volkovmedia.feature.munchkinlist.domain.MunchkinDto
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListIntent
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListIntent.UpdateMunchkin
import com.volkovmedia.feature.munchkinlist.presentation.mvi.MunchkinListState
import com.volkovmedia.feature.munchkinlist.presentation.recycler.MunchkinListAdapter
import kotlinx.android.synthetic.main.munchkinlist_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf

internal class MunchkinListFragment : MviFragment<MunchkinListIntent, MunchkinListState, Nothing>(
    initialIntent = MunchkinListIntent.LoadData
) {

    override val layoutResource = R.layout.munchkinlist_fragment

    private val teamId by argumentLong(KEY_TEAMID)

    private val adapter = MunchkinListAdapter(
        ::onMunchkinClick,
        ::onMunchkinLevelRaiseUpClick,
        ::onMunchkinGearRaiseUpClick
    )

    override fun provideViewModel() = getViewModel<MunchkinListViewModel> { parametersOf(teamId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        munchkinlist_recycler.layoutManager = LinearLayoutManager(context)
        munchkinlist_recycler.adapter = adapter

        munchkinlist_fab.onClick = { postIntent(MunchkinListIntent.CreateMunchkin("asdsa", MunchkinGender.MALE)) }
    }

    override fun render(state: MunchkinListState) {
        munchkinlist_progressbar.isVisible = state.isLoading
        adapter.items = state.payload
    }

    private fun onMunchkinClick(munchkin: MunchkinDto) {}

    private fun onMunchkinLevelRaiseUpClick(munchkinDto: MunchkinDto, raiseUp: Boolean) = munchkinDto.postUpdate {
        val modificator = if (raiseUp) 1 else -1
        copy(level = level + modificator)
    }

    private fun onMunchkinGearRaiseUpClick(munchkinDto: MunchkinDto, raiseUp: Boolean) = munchkinDto.postUpdate {
        val modificator = if (raiseUp) 1 else -1
        copy(gear = gear + modificator)
    }

    private fun MunchkinDto.postUpdate(body: Munchkin.() -> Munchkin) {
        postIntent(UpdateMunchkin(body.invoke(munchkin)))
    }

    companion object {

        private const val KEY_TEAMID = "teamId"

        fun newInstance(teamId: Long) = MunchkinListFragment().withArguments {
            putLong(KEY_TEAMID, teamId)
        }

    }

}