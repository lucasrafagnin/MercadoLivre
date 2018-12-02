package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.Installment
import br.com.mercadolivre.data.repository.PaymentRepository
import io.reactivex.Observable
import mobi.porquenao.sovai.exception.EmptyStateException
import javax.inject.Inject

class GetInstallments @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(paymentMethod: String, amount: Double, issuerId: String): Observable<Installment> =
            repository.getInstallments(paymentMethod, amount, issuerId)
                    .map { it[0] }
                    .switchIfEmpty { it.onError(EmptyStateException()) }

}
