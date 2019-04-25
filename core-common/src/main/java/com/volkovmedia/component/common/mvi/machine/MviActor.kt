package com.volkovmedia.component.common.mvi.machine

import io.reactivex.Observable

abstract class MviActor<State : Any, Intent : Any, Action : Any> {

    open fun act(state: State, intent: Intent): Observable<out Action> = Observable.empty()

}