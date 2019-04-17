package com.volkovmedia.supermunchkin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.transaction
import com.volkovmedia.feature.teamlist.FRAGMENT_TEAMLIST
import com.volkovmedia.feature.teamlist.presentation.TeamListNavigator
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(), TeamListNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.transaction {
            add(R.id.container, get(FRAGMENT_TEAMLIST))
            addToBackStack("teamlist")
        }
    }

    override fun navigateToTeam(teamId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}