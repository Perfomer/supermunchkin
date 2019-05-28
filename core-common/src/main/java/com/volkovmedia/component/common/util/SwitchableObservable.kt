package com.volkovmedia.component.common.util

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

fun <T : Any> Observable<T>.switchSource(source: Observable<T>): Observable<T> =
    if (this is SwitchableObservable<T>) this.apply { switchSourceObservable(source) }
    else from(source)

fun <T : Any> from(source: Observable<T>) = SwitchableObservable(source)

class SwitchableObservable<T : Any> internal constructor(
    private var source: Observable<T>
) : Observable<T>() {

    private val disposable = CompositeDisposable()

    private var observer: Observer<in T>? = null

    override fun subscribeActual(observer: Observer<in T>?) {
        this.observer = observer
        updateSubscription()
    }

    internal fun switchSourceObservable(source: Observable<T>) {
        this.source = source
        updateSubscription()
    }

    private fun updateSubscription() {
        disposable.clear()

        if (observer == null) return

        disposable += source
            .subscribeOn(Schedulers.io())
            .subscribeBy { observer?.onNext(it) }
    }

}