package com.volkovmedia.feature.teamlist.presentation.mvi

import com.volkovmedia.component.data.model.entity.Team

internal sealed class TeamListIntent {

    object LoadData: TeamListIntent()

    class RemoveTeam(val team: Team): TeamListIntent()

}