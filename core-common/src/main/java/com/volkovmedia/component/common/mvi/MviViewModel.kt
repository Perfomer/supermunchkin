package com.volkovmedia.component.common.mvi

import androidx.lifecycle.ViewModel
import com.volkovmedia.component.common.util.flatWithLatestFrom
import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.rxkotlin.withLatestFrom
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject

abstract class MviViewModel<Intent : Any, Action : Any, State : Any, Subscription : Any>(
    initialState: State
) : ViewModel() {

    val subscription: ObservableSource<Subscription>
        get() = subscriptionSubject

    val state: ObservableSource<State>
        get() = stateSubject

    private val intentSubject = PublishSubject.create<Intent>()
    private val subscriptionSubject = PublishSubject.create<Subscription>()
    private val stateSubject = BehaviorSubject.create<State>()

    private val disposable = CompositeDisposable()

    init {
        stateSubject.onNext(initialState)

        disposable += intentSubject
            .flatWithLatestFrom(state, ::onIntentReceived)
            .withLatestFrom(state, ::onActionReceived)
            .distinctUntilChanged()
            .subscribeBy(onNext = stateSubject::onNext)
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


    fun postIntent(intent: Intent) = intentSubject.onNext(intent)


    protected open fun act(state: State, intent: Intent): Observable<out Action> = Observable.empty()

    protected open fun reduce(oldState: State, action: Action): State = oldState

    protected open fun publishSubscription(state: State, action: Action): Subscription? = null


    private fun onIntentReceived(intent: Intent, state: State) = act(state, intent)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())

    private fun onActionReceived(action: Action, oldState: State): State {
        val newState = reduce(oldState, action)
        val subscription = publishSubscription(newState, action)

        subscription?.let(subscriptionSubject::onNext)

        return newState
    }

}