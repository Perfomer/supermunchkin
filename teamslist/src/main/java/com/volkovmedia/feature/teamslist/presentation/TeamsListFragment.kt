package com.volkovmedia.feature.teamslist.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkovmedia.commons.mvi.MviFragment
import com.volkovmedia.commons.util.currentDate
import com.volkovmedia.commons.util.onClick
import com.volkovmedia.commons.util.toast
import com.volkovmedia.coredata.model.entity.Team
import com.volkovmedia.feature.teamslist.R
import com.volkovmedia.feature.teamslist.presentation.dialog.TeamNameDialog
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListIntent
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListIntent.*
import com.volkovmedia.feature.teamslist.presentation.mvi.TeamsListState
import com.volkovmedia.feature.teamslist.presentation.recycler.TeamsListAdapter
import kotlinx.android.synthetic.main.teamslist_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

class TeamsListFragment : MviFragment<TeamsListIntent, TeamsListState, Nothing>(
    initialIntent = LoadData
) {

    override val layoutResource = R.layout.teamslist_fragment

    private val navigator by lazy { activity as TeamsListNavigator }

    private val adapter = TeamsListAdapter(::onTeamClick, ::onTeamLongClick)


    override fun provideViewModel() = getViewModel<TeamsListViewModel>()

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

    override fun render(state: TeamsListState) {
        Log.d("RENDER", state.toString())
        teamslist_progressbar.isVisible = state.isLoading
        adapter.items = state.payload
    }


    private fun onTeamClick(team: Team) {
        postIntent(UpdateTeam(team.copy(lastGameDate = currentDate)))
        navigator.navigateToTeam(team.id)
    }

    private fun onTeamLongClick(team: Team) {
        postIntent(RemoveTeam(team)) //todo action mode
    }

}