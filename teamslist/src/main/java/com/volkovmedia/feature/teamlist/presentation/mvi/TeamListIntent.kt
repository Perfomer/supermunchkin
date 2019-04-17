package com.volkovmedia.feature.teamlist.presentation.mvi

import com.volkovmedia.coredata.model.entity.Team

internal sealed class TeamListIntent {

    object LoadData: TeamListIntent()

    class CreateTeam(val name: String): TeamListIntent()

    class UpdateTeam(val team: Team): TeamListIntent()

    class RemoveTeam(val team: Team): TeamListIntent()

}