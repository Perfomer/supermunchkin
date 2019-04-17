package com.volkovmedia.feature.teamslist.presentation.mvi

import com.volkovmedia.coredata.model.entity.Team

sealed class TeamsListIntent {

    object LoadData: TeamsListIntent()

    class CreateTeam(val name: String): TeamsListIntent()

    class UpdateTeam(val team: Team): TeamsListIntent()

    class RemoveTeam(val team: Team): TeamsListIntent()

}