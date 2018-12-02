package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.GetInstallments
import br.com.mercadolivre.domain.usecase.GetPaymentCache
import br.com.mercadolivre.domain.usecase.SavePaymentCache
import br.com.mercadolivre.presentation.mapper.InstallmentMapper
import br.com.mercadolivre.ui.fragment.InstallmentView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class InstallmentPresenter @Inject constructor(
        private val getInstallments: GetInstallments,
        private val getPaymentCache: GetPaymentCache,
        private val savePaymentCache: SavePaymentCache,
        private val mapper: InstallmentMapper
) : BasePresenter<InstallmentView>() {

    fun onStart() {
        view?.showLoading()

        val payment = getPaymentCache.execute()
        val paymentMethodId = payment.paymentMethodId ?: throw IllegalStateException()
        val cardIssuerId = payment.cardIssuerId ?: throw IllegalStateException()
        val amount = payment.amount ?: throw IllegalStateException()
        val installments = payment.installments
        view?.setInstallmentSelected(installments)

        addDisposable(getInstallments.execute(paymentMethodId, amount, cardIssuerId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ view?.showItems(mapper.map(it)) },
                        { it.printStackTrace() }))
    }

    fun selectItem(installments: Int) = savePaymentCache.execute(installments = installments)

}
