package com.volkovmedia.supermunchkin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.transaction
import com.volkovmedia.feature.teamslist.presentation.TeamsListFragment
import com.volkovmedia.feature.teamslist.presentation.TeamsListNavigator
import org.koin.android.ext.android.get

class MainActivity : AppCompatActivity(), TeamsListNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.transaction { add(R.id.container, get<TeamsListFragment>()) }
    }

    override fun navigateToTeam(teamId: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}