package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.ClearPaymentCache
import br.com.mercadolivre.navigation.NavigationController
import br.com.mercadolivre.ui.activity.PaymentView
import javax.inject.Inject

class PaymentPresenter @Inject constructor(
        private val navigation: NavigationController,
        private val clearPaymentCache: ClearPaymentCache
) : BasePresenter<PaymentView>() {

    private var currentPage = 0

    fun onStart() {
        view?.chooseTab(currentPage, currentPage)
        view?.enableNext(false)
    }

    fun nextPage() {
        if (currentPage < 2) {
            currentPage++
            view?.chooseTab(currentPage - 1, currentPage)
            view?.enableNext(false)
        } else {
            navigation.launchPopupSuccess()
            view?.close()
        }
    }

    fun backPage() {
        if (currentPage > 0) {
            currentPage--
            view?.chooseTab(currentPage + 1, currentPage)
            view?.enableNext(true)
        } else {
            clearPaymentCache.execute()
            view?.close()
        }
    }

}
