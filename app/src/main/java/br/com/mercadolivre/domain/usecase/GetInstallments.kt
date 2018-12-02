package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.Installment
import br.com.mercadolivre.data.repository.PaymentRepository
import br.com.mercadolivre.exception.EmptyStateException
import io.reactivex.Observable
import javax.inject.Inject

class GetInstallments @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(paymentMethod: String, amount: Double, issuerId: String): Observable<Installment> =
            repository.getInstallments(paymentMethod, amount, issuerId)
                    .map { if (it.isEmpty()) throw EmptyStateException() else it[0] }

}
