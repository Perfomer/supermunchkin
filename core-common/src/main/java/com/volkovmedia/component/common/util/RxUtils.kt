package com.volkovmedia.component.common.util

import io.reactivex.Observable
import io.reactivex.ObservableSource
import io.reactivex.rxkotlin.withLatestFrom

fun <T> ObservableSource<T>.toObservable() = Observable.wrap(this)

fun <T> T.toObservable() = Observable.just(this)

inline fun <T, U, R> Observable<T>.flatWithLatestFrom(
    other: ObservableSource<U>,
    crossinline combiner: (T, U) -> ObservableSource<out R>
): Observable<R> {
    return withLatestFrom(other, combiner).flatMap { it }
}