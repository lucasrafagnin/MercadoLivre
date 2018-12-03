package br.com.mercadolivre.data.model

data class Payment(
        val amount: Double? = .0,
        val amountFormatted: String? = null,
        val paymentMethodId: String? = null,
        val cardIssuerId: String? = null,
        val installments: Int? = 0
)
