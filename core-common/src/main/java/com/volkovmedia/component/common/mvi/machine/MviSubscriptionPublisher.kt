package com.volkovmedia.component.common.mvi.machine

abstract class MviSubscriptionPublisher<State : Any, Action : Any, Subscription : Any> {

    open fun publishSubscription(state: State, action: Action): Subscription? = null

}