package br.com.mercadolivre.data.datasource.cache

import br.com.mercadolivre.data.model.Payment
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PaymentCache @Inject constructor() {

    private var cache = Payment()

    fun save(payment: Payment) {
        cache = payment.let {
            cache.copy(
                    amount = if (it.amount != .0) it.amount else cache.amount,
                    amountFormatted = it.amountFormatted ?: cache.amountFormatted,
                    paymentMethodId = it.paymentMethodId ?: cache.paymentMethodId,
                    cardIssuerId = it.cardIssuerId ?: cache.cardIssuerId,
                    installments = if (it.installments != 0) it.installments else cache.installments
            )
        }
    }

    fun get() = cache

    fun clear() {
        cache = Payment()
    }

}
