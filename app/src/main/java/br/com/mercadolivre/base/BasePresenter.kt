package br.com.mercadolivre.base

import br.com.mercadolivre.R
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

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

    fun handleError(e: Throwable) = when (e) {
        is UnknownHostException -> view?.showConnectionError()
        else -> view?.showMessage(R.string.app_name)
    }

}
