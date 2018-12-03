package br.com.mercadolivre.presentation.presenter

import br.com.mercadolivre.base.BasePresenter
import br.com.mercadolivre.domain.usecase.ClearPaymentCache
import br.com.mercadolivre.domain.usecase.GetPaymentCache
import br.com.mercadolivre.presentation.mapper.PopupMapper
import br.com.mercadolivre.ui.activity.PopupSuccessView
import javax.inject.Inject

class PopupSuccessPresenter @Inject constructor(
        private val mapper: PopupMapper,
        private val getPaymentCache: GetPaymentCache,
        private val clearPaymentCache: ClearPaymentCache
) : BasePresenter<PopupSuccessView>() {

    fun onStart() = view?.setDescription(mapper.map(getPaymentCache.execute()))

    fun clearCache() = clearPaymentCache.execute()

}
