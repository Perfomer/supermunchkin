package com.volkovmedia.feature.munchkinlist.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.argumentLong
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.munchkinlist.R
import com.volkovmedia.feature.munchkinlist.domain.model.MunchkinDto
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

    override val menuResource = R.menu.munchkinlist_menu


    private val navigator by lazy { activity as MunchkinListNavigator }

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

        munchkinlist_fab.onClick = { navigator.navigateToBattle() }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.munchkinlist_editteam -> currentState?.let { navigator.navigateToTeamEdit(it.teamDto.id) }
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun render(state: MunchkinListState) {
        val teamDto = state.teamDto

        munchkinlist_toolbar.title = teamDto.team.name
        adapter.items = teamDto.participants

        munchkinlist_progressbar.isVisible = state.isLoading
    }

    private fun onMunchkinClick(munchkin: MunchkinDto) = navigator.navigateToMunchkinEdit(munchkin.id)

    private fun onMunchkinLevelRaiseUpClick(munchkinDto: MunchkinDto, raiseUp: Boolean) = munchkinDto.postUpdate {
        val modificator = if (raiseUp) 1 else -1
        this.copy(level = level + modificator)
    }

    private fun onMunchkinGearRaiseUpClick(munchkinDto: MunchkinDto, raiseUp: Boolean) = munchkinDto.postUpdate {
        val modificator = if (raiseUp) 1 else -1
        this.copy(gear = gear + modificator)
    }

    private fun MunchkinDto.postUpdate(body: Munchkin.() -> Munchkin) = postIntent(
        UpdateMunchkin(body.invoke(munchkin))
    )


    internal companion object {

        private const val KEY_TEAMID = "teamId"

        fun newInstance(teamId: Long) = MunchkinListFragment().withArguments {
            putLong(KEY_TEAMID, teamId)
        }

    }

}