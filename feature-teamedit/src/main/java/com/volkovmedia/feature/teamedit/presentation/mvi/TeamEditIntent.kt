package com.volkovmedia.feature.teamedit.presentation.mvi

import com.volkovmedia.component.data.model.MunchkinGender
import com.volkovmedia.component.data.model.entity.Munchkin

internal sealed class TeamEditIntent {

    object LoadData : TeamEditIntent()

    class EditTeamName(val name: String) : TeamEditIntent()

    class AddMunchkin(val munchkin: Munchkin) : TeamEditIntent()

    class EditMunchkin(val oldName: String, val newName: String, val newGender: MunchkinGender) : TeamEditIntent()

    class RemoveMunchkin(val munchkin: Munchkin) : TeamEditIntent()

    object SaveTeamDetails: TeamEditIntent()

}