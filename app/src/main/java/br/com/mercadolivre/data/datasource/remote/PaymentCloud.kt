package br.com.mercadolivre.data.datasource.remote

import br.com.mercadolivre.data.model.CardIssuer
import br.com.mercadolivre.data.model.Installment
import br.com.mercadolivre.data.model.PaymentMethod
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface PaymentCloud {

    @GET("payment_methods")
    fun getPaymentMethods(): Observable<List<PaymentMethod>>

    @GET("payment_methods/card_issuers")
    fun getCardIssuers(
            @Query("payment_method_id") paymentMethod: String
    ): Observable<List<CardIssuer>>

    @GET("payment_methods/installments")
    fun getInstallments(
            @Query("payment_method_id") paymentMethod: String,
            @Query("amount") amount: Double,
            @Query("card_issuer.id") issuerId: String
    ): Observable<List<Installment>>

}
