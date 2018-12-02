package br.com.mercadolivre.base

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView> {

    private val subscriptions = CompositeDisposable()
    protected var view: V? = null

    fun onAttachView(view: V) {
        this.view = view
    }

    fun onDetachView() {
        view = null
        subscriptions.clear()
    }

    fun addDisposable(disposable: Disposable) = subscriptions.add(disposable)

}
