package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.SavePaymentCache
import br.com.mercadolivre.navigation.NavigationController
import br.com.mercadolivre.ui.activity.HomeView
import javax.inject.Inject

class HomePresenter @Inject constructor(
        private val navigation: NavigationController,
        private val savePaymentCache: SavePaymentCache
) : BasePresenter<HomeView>() {

    fun launchPaymentMethod() = navigation.launchPayment()

    fun savePrice(amount: Double, amountFormatted: String) = savePaymentCache.execute(amount, amountFormatted)

}
