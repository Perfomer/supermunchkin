package com.volkovmedia.feature.teamedit.presentation

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import com.jakewharton.rxbinding3.widget.textChanges
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.argumentLong
import com.volkovmedia.component.common.util.init
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.component.data.model.entity.Munchkin
import com.volkovmedia.feature.teamedit.R
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditIntent
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditIntent.RemoveMunchkin
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditState
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditSubscription
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditSubscription.TeamSavingFailed
import com.volkovmedia.feature.teamedit.presentation.mvi.TeamEditSubscription.TeamSavingSucceed
import com.volkovmedia.feature.teamedit.presentation.recycler.TeamEditAdapter
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.android.synthetic.main.teamedit_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.parameter.parametersOf
import java.util.concurrent.TimeUnit

internal class TeamEditFragment : MviFragment<TeamEditIntent, TeamEditState, TeamEditSubscription>() {

    override val layoutResource = R.layout.teamedit_fragment

    private val navigator by lazy { activity as TeamEditNavigator }

    private val adapter = TeamEditAdapter(::onMunchkinClick, ::onMunchkinDeleteClick)

    private val teamId by argumentLong(KEY_TEAMID)


    override fun provideViewModel() = getViewModel<TeamEditViewModel> { parametersOf(teamId) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        teamedit_toolbar.setTitle(
            if (teamId == null) R.string.teamedit_teamcreate
            else R.string.teamedit_teamedit
        )

        teamedit_toolbar.attachToActivity()
        teamedit_participants.init(adapter)

        teamedit_participants_add.onClick = { /* todo show dialog */ }
        teamedit_fab.onClick = { postIntent(TeamEditIntent.SaveTeamDetails) }

        disposable += teamedit_name.textChanges()
            .debounce(500, TimeUnit.MILLISECONDS)
            .distinctUntilChanged()
            .subscribeBy { postIntent(TeamEditIntent.EditTeamName(it.toString())) }
    }

    override fun render(state: TeamEditState) {
        val teamDto = state.teamDto
        val teamName = teamDto.team.name

        if (teamedit_name.text.toString() != teamName) teamedit_name.setText(teamName)

        teamedit_progressbar.isVisible = state.isLoading
        adapter.items = teamDto.participants
    }

    override fun onSubscriptionReceived(subscription: TeamEditSubscription) = when (subscription) {
        is TeamSavingSucceed -> navigator.navigateToTeam(subscription.teamId)
        is TeamSavingFailed -> TODO()
    }


    private fun onMunchkinDeleteClick(munchkin: Munchkin) = postIntent(RemoveMunchkin(munchkin))

    private fun onMunchkinClick(munchkin: Munchkin) {
        /* todo show dialog */
    }

    internal companion object {

        private const val KEY_TEAMID = "teamId"

        internal fun newInstance(teamId: Long?) = TeamEditFragment().withArguments {
            teamId?.let { putLong(KEY_TEAMID, it) }
        }

    }

}