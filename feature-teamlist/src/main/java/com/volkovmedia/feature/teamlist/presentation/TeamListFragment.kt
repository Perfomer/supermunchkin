package com.volkovmedia.feature.teamlist.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.init
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.feature.teamlist.R
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent.LoadData
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent.RemoveTeam
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListState
import com.volkovmedia.feature.teamlist.presentation.recycler.TeamListAdapter
import kotlinx.android.synthetic.main.teamslist_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

internal class TeamListFragment : MviFragment<TeamListIntent, TeamListState, Nothing>(
    layoutResource = R.layout.teamslist_fragment,
    initialIntent = LoadData
) {

    private val navigator by lazy { activity as TeamListNavigator }

    private val adapter = TeamListAdapter(::onTeamClick, ::onTeamLongClick)


    override fun provideViewModel() = getViewModel<TeamListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamslist_toolbar.attachToActivity(enableArrowUp = false)
        teamslist_recycler.init(adapter)

        teamslist_fab.onClick = navigator::navigateToTeamCreate
    }

    override fun render(state: TeamListState) {
        teamslist_progressbar.isVisible = state.isLoading
        adapter.items = state.payload
    }


    private fun onTeamClick(item: TeamDto) = navigator.navigateToTeam(item.id)

    private fun onTeamLongClick(item: TeamDto) {
        postIntent(RemoveTeam(item.team)) //todo action mode
    }

}