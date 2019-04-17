package com.volkovmedia.commons.mvi

import androidx.lifecycle.ViewModel
import com.badoo.mvicore.feature.ActorReducerFeature
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

abstract class MviViewModel<Intent : Any, Action : Any, State : Any, Subscription : Any>(
    private val initialState: State
) : ViewModel() {

    val subscription: ObservableSource<Subscription> by lazy { feature.news }

    val state: ObservableSource<State> by lazy { feature }

    private val feature by lazy {
        ActorReducerFeature(
            initialState = initialState,
            actor = ::actWithSchedulers,
            reducer = ::reduce,
            newsPublisher = ::publishSubscription
        )
    }

    fun postIntent(intent: Intent) = feature.accept(intent)

    protected open fun act(state: State, intent: Intent): Observable<out Action> = Observable.empty()

    protected open fun reduce(oldState: State, action: Action): State = oldState

    protected open fun publishSubscription(intent: Intent, action: Action, state: State): Subscription? = null

    private fun actWithSchedulers(state: State, intent: Intent) = act(state, intent)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

}