package br.com.mercadolivre.data.repository

import br.com.mercadolivre.data.datasource.cache.PaymentCache
import br.com.mercadolivre.data.datasource.remote.PaymentCloud
import br.com.mercadolivre.data.model.CardIssuer
import br.com.mercadolivre.data.model.Installment
import br.com.mercadolivre.data.model.Payment
import br.com.mercadolivre.data.model.PaymentMethod
import io.reactivex.Observable
import javax.inject.Inject

class PaymentRepository @Inject constructor(
        private val cloud: PaymentCloud,
        private val cache: PaymentCache
) {

    fun getPaymentMethods(): Observable<List<PaymentMethod>> =
            cloud.getPaymentMethods()

    fun getCardIssuers(paymentMethod: String): Observable<List<CardIssuer>> =
            cloud.getCardIssuers(paymentMethod)

    fun getInstallments(paymentMethod: String, amount: Double, issuerId: String): Observable<List<Installment>> =
            cloud.getInstallments(paymentMethod, amount, issuerId)

    fun getPayment() = cache.get()

    fun savePayment(payment: Payment) = cache.save(payment)

    fun makePayment() = cache.clear()

    fun clearCache() = cache.clear()

}
