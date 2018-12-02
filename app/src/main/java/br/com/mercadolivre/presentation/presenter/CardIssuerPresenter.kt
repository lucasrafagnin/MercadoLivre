package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.GetCardIssuers
import br.com.mercadolivre.domain.usecase.GetPaymentCache
import br.com.mercadolivre.domain.usecase.SavePaymentCache
import br.com.mercadolivre.exception.EmptyStateException
import br.com.mercadolivre.presentation.mapper.CardIssuerMapper
import br.com.mercadolivre.ui.fragment.CardIssuerView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CardIssuerPresenter @Inject constructor(
        private val getCardIssuers: GetCardIssuers,
        private val getPaymentCache: GetPaymentCache,
        private val savePaymentCache: SavePaymentCache,
        private val mapper: CardIssuerMapper
) : BasePresenter<CardIssuerView>() {

    fun onStart() {
        view?.showLoading()

        val payment = getPaymentCache.execute()
        val paymentMethodId = payment.paymentMethodId ?: throw IllegalStateException()
        val cardIssuerId = payment.cardIssuerId
        view?.setIssuerSelected(cardIssuerId)

        addDisposable(getCardIssuers.execute(paymentMethodId)
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

    fun selectItem(issuerId: String) = savePaymentCache.execute(issuerId = issuerId)

}
