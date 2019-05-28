package com.volkovmedia.component.common.mvi

import com.volkovmedia.component.common.util.toObservable
import com.volkovmedia.component.common.view.BaseFragment
import io.reactivex.ObservableSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

abstract class MviFragment<Intent : Any, State : Any, Subscription : Any>(
    private val initialIntent: Intent? = null
) : BaseFragment() {

    protected var currentState: State? = null
        private set

    private val viewModel by lazy { provideViewModel() }

    override fun onStart() {
        super.onStart()

        disposable.addAll(
            viewModel.state bindTo ::onStateReceived,
            viewModel.subscription bindTo ::onSubscriptionReceived
        )
    }

    override fun onResume() {
        super.onResume()
        initialIntent?.let(::postIntent)
    }

    protected abstract fun provideViewModel(): MviViewModel<Intent, *, State, Subscription>

    protected abstract fun render(state: State)

    protected open fun onSubscriptionReceived(subscription: Subscription) {}

    protected fun postIntent(intent: Intent) = viewModel.postIntent(intent)


    private fun onStateReceived(state: State) {
        currentState = state
        render(state)
    }


    private companion object {

        private infix fun <T : Any> ObservableSource<T>.bindTo(consumer: (T) -> Unit): Disposable {
            return this.toObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeBy(onNext = consumer)
        }

    }

}