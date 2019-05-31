package com.volkovmedia.feature.battle.presentation.mvi

internal sealed class BattleIntent {

    class AddMunchkin(val munchkinId: Long): BattleIntent()

    object AddMonster: BattleIntent()

}