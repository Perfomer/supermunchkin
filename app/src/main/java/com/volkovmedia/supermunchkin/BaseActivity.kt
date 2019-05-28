package com.volkovmedia.supermunchkin

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.volkovmedia.supermunchkin.navigation.ScreenDestination
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.android.support.SupportAppNavigator

abstract class BaseActivity(
    @LayoutRes private val screenLayoutResource: Int,
    @IdRes private val frameLayoutResource: Int,
    private val startScreen: ScreenDestination
) : AppCompatActivity() {

    private val navigatorHolder by inject<NavigatorHolder>()

    private val navigator by inject<SupportAppNavigator> { parametersOf(this, frameLayoutResource) }

    private val router by inject<Router>()


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        setContentView(screenLayoutResource)

        if (savedInstanceState == null) {
            router.newRootScreen(startScreen)
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    override fun onBackPressed() = router.exit()


    protected fun navigateTo(screen: ScreenDestination) = router.navigateTo(screen)

}