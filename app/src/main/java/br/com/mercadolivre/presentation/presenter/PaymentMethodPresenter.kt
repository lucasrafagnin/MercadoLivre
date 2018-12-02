package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.GetPaymentCache
import br.com.mercadolivre.domain.usecase.GetPaymentMethods
import br.com.mercadolivre.domain.usecase.SavePaymentCache
import br.com.mercadolivre.exception.EmptyStateException
import br.com.mercadolivre.presentation.mapper.PaymentMethodMapper
import br.com.mercadolivre.ui.fragment.PaymentMethodView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PaymentMethodPresenter @Inject constructor(
        private val getPaymentMethods: GetPaymentMethods,
        private val getPaymentCache: GetPaymentCache,
        private val savePaymentCache: SavePaymentCache,
        private val mapper: PaymentMethodMapper
) : BasePresenter<PaymentMethodView>() {

    fun onStart() {
        view?.showLoading()

        val payment = getPaymentCache.execute()
        view?.setMethodSelected(payment.paymentMethodId)

        addDisposable(getPaymentMethods.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    view?.showItems(mapper.map(it))
                }, {
                    when (it) {
                        is EmptyStateException -> view?.showEmpty()
                        else -> view?.showError()
                    }
                }))
    }

    fun selectItem(methodId: String) = savePaymentCache.execute(paymentMethodId = methodId)

}
