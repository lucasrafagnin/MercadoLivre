package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.CardIssuer
import br.com.mercadolivre.data.repository.PaymentRepository
import br.com.mercadolivre.exception.EmptyStateException
import io.reactivex.Observable
import javax.inject.Inject

class GetCardIssuers @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(paymentMethod: String): Observable<List<CardIssuer>> =
            repository.getCardIssuers(paymentMethod)
                    .map { if (it.isEmpty()) throw EmptyStateException() else it }

}
