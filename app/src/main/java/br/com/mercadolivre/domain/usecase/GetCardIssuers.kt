package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.CardIssuer
import br.com.mercadolivre.data.repository.PaymentRepository
import io.reactivex.Observable
import mobi.porquenao.sovai.exception.EmptyStateException
import javax.inject.Inject

class GetCardIssuers @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(paymentMethod: String): Observable<List<CardIssuer>> =
            repository.getCardIssuers(paymentMethod)
                    .switchIfEmpty { it.onError(EmptyStateException()) }

}
