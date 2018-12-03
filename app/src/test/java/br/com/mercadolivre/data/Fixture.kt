package br.com.mercadolivre.data

import br.com.mercadolivre.data.model.*

object Fixture {

    const val amount = 100.0
    const val amountFormatted = "R$100.00"
    const val paymentMethodId = "master"
    const val cardIssuerId = "326"
    const val installmentsId = 3

    val paymentMethod =
            PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif", PaymentType.CREDIT)

    val paymentMethods = listOf(
            PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif", PaymentType.CREDIT),
            PaymentMethod("master", "Master", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif", PaymentType.CREDIT),
            PaymentMethod("pagofacil", "PagoFacil", "http://img.mlstatic.com/org-img/MP3/API/logos/pagofacil.gif", PaymentType.TICKET),
            PaymentMethod("maestro", "Maestro", "http://img.mlstatic.com/org-img/MP3/API/logos/maestro.gif", PaymentType.DEBIT)
    )

    val invalidPaymentMethods = listOf(
            PaymentMethod("pagofacil", "PagoFacil", "http://img.mlstatic.com/org-img/MP3/API/logos/pagofacil.gif", PaymentType.TICKET),
            PaymentMethod("maestro", "Maestro", "http://img.mlstatic.com/org-img/MP3/API/logos/maestro.gif", PaymentType.DEBIT)
    )

    val cardIssuer =
            CardIssuer("1078", "Mercado Pago + Banco Patagonia", "http://img.mlstatic.com/org-img/MP3/API/logos/1078.gif")

    val cardIssuers = listOf(
            CardIssuer("1078", "Mercado Pago + Banco Patagonia", "http://img.mlstatic.com/org-img/MP3/API/logos/1078.gif"),
            CardIssuer("692", "Cencosud", "http://img.mlstatic.com/org-img/MP3/API/logos/692.gif"),
            CardIssuer("272", "Banco Comafi", "http://img.mlstatic.com/org-img/MP3/API/logos/272.gif")
    )

    val installment = Installment(costs = listOf(
            Cost("1 cuota de \$ 25,90 (\$ 25,90)", 1),
            Cost("3 cuotas de \$ 10,72 (\$ 32,16)", 3),
            Cost("6 cuotas de \$ 6,20 (\$ 37,18)", 6)
    ))

    val installments = listOf(Installment(costs = listOf(
            Cost("1 cuota de \$ 25,90 (\$ 25,90)", 1),
            Cost("3 cuotas de \$ 10,72 (\$ 32,16)", 3),
            Cost("6 cuotas de \$ 6,20 (\$ 37,18)", 6)
    )))

    val payment = Payment(amount, amountFormatted, paymentMethodId, cardIssuerId, installmentsId)

}
