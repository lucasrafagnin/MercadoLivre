package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.repository.PaymentRepository
import javax.inject.Inject

class ClearPaymentCache @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute() = repository.clearCache()

}
