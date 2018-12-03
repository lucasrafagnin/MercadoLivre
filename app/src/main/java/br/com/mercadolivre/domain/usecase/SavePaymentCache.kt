package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.Payment
import br.com.mercadolivre.data.repository.PaymentRepository
import javax.inject.Inject

class SavePaymentCache @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(amount: Double? = .0,
                amountFormatted: String? = null,
                paymentMethodId: String? = null,
                issuerId: String? = null,
                installments: Int? = 0) =
            repository.savePayment(Payment(amount, amountFormatted, paymentMethodId, issuerId, installments))

}
