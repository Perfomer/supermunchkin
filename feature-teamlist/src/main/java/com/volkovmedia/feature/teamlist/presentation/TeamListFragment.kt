package com.volkovmedia.feature.teamlist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.currentDate
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.common.util.toast
import com.volkovmedia.component.data.model.dto.TeamDto
import com.volkovmedia.feature.teamlist.R
import com.volkovmedia.feature.teamlist.presentation.dialog.TeamNameDialog
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListIntent.*
import com.volkovmedia.feature.teamlist.presentation.mvi.TeamListState
import com.volkovmedia.feature.teamlist.presentation.recycler.TeamListAdapter
import kotlinx.android.synthetic.main.teamslist_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

internal class TeamListFragment : MviFragment<TeamListIntent, TeamListState, Nothing>(
    initialIntent = LoadData
) {

    override val layoutResource = R.layout.teamslist_fragment

    private val navigator by lazy { activity as TeamListNavigator }

    private val adapter = TeamListAdapter(::onTeamClick, ::onTeamLongClick)


    override fun provideViewModel() = getViewModel<TeamListViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamslist_recycler.layoutManager = LinearLayoutManager(context)
        teamslist_recycler.adapter = adapter

        teamslist_fab.onClick = {
            TeamNameDialog(context!!).show {
                if (it.isBlank()) toast("Название команды не должно быть пустым")
                else postIntent(CreateTeam(it))
            }
        }
    }

    override fun render(state: TeamListState) {
        Log.d("RENDER", state.toString())
        teamslist_progressbar.isVisible = state.isLoading
        adapter.items = state.payload
    }


    private fun onTeamClick(item: TeamDto) {
        postIntent(UpdateTeam(item.team.copy(lastGameDate = currentDate)))
        navigator.navigateToTeam(item.id)
    }

    private fun onTeamLongClick(item: TeamDto) {
        postIntent(RemoveTeam(item.team)) //todo action mode
    }

}