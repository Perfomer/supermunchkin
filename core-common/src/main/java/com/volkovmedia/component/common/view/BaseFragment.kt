package com.volkovmedia.component.common.view

import android.os.Bundle
import android.view.*
import androidx.annotation.LayoutRes
import androidx.annotation.MenuRes
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment

@Suppress("unused")
abstract class BaseFragment : Fragment() {

    @LayoutRes
    protected open val layoutResource: Int? = null

    @MenuRes
    protected open val menuResource: Int? = null

    protected val appCompatActivity: AppCompatActivity
        get() = activity as AppCompatActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        menuResource?.let { setHasOptionsMenu(true) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return if (layoutResource == null) super.onCreateView(inflater, container, savedInstanceState)
        else inflater.inflate(layoutResource!!, container, false)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menuResource?.let { inflater.inflate(it, menu) }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> activity?.onBackPressed()
            else -> return super.onOptionsItemSelected(item)
        }

        return true
    }

    protected fun Toolbar.attachToActivity() {
        appCompatActivity.setSupportActionBar(this)
        appCompatActivity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    protected companion object {

        /**
         * Прикрепление аргументов из лямбды к фрагменту
         */
        fun <F : BaseFragment> F.withArguments(bundleInitialization: Bundle.() -> Unit): F {
            arguments = Bundle().apply { bundleInitialization(this) }
            return this
        }

    }

}