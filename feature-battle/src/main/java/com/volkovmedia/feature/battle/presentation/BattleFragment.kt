package com.volkovmedia.feature.battle.presentation

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.isVisible
import com.volkovmedia.component.common.mvi.MviFragment
import com.volkovmedia.component.common.util.init
import com.volkovmedia.component.common.util.onClick
import com.volkovmedia.feature.battle.R
import com.volkovmedia.feature.battle.presentation.mvi.BattleIntent
import com.volkovmedia.feature.battle.presentation.mvi.BattleState
import com.volkovmedia.feature.battle.presentation.mvi.BattleSubscription
import com.volkovmedia.feature.battle.presentation.recycler.monster.MonsterAdapter
import com.volkovmedia.feature.battle.presentation.recycler.munchkin.MunchkinAdapter
import kotlinx.android.synthetic.main.battle_fragment.*
import org.koin.androidx.viewmodel.ext.android.getViewModel

internal class BattleFragment : MviFragment<BattleIntent, BattleState, BattleSubscription>(
    layoutResource = R.layout.battle_fragment,
    menuResource = R.menu.battle_menu
) {

    private val monsterAdapter = MonsterAdapter()

    private val munchkinAdapter = MunchkinAdapter()


    override fun provideViewModel() = getViewModel<BattleViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        battle_munchkins_recycler.init(munchkinAdapter)
        battle_monsters_recycler.init(monsterAdapter)

        battle_munchkins_add.onClick = {}
        battle_monsters_add.onClick = {}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.battle_rolldice -> {}
            else -> super.onOptionsItemSelected(item)
        }

        return true
    }

    override fun render(state: BattleState) {
        battle_progressbar.isVisible = state.isLoading

        battle_munchkins_strength.text = state.munchkinsStrength.toString()
        battle_monsters_strength.text = state.monstersStrength.toString()

        monsterAdapter.items = state.monsters
        munchkinAdapter.items = state.munchkins
    }

}