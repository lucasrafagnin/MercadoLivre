package br.com.mercadolivre.data

import br.com.mercadolivre.data.model.*

object Fixture {

    val paymentMethod =
            PaymentMethod("visa", "Visa", "http://img.mlstatic.com/org-img/MP3/API/logos/visa.gif", PaymentType.CREDIT)

    val cardIssuer =
            CardIssuer("1078", "Mercado Pago + Banco Patagonia", "http://img.mlstatic.com/org-img/MP3/API/logos/1078.gif")

    val installments = Installment(costs = listOf(
            Cost("1 cuota de \$ 25,90 (\$ 25,90)", 1),
            Cost("3 cuotas de \$ 10,72 (\$ 32,16)", 3),
            Cost("6 cuotas de \$ 6,20 (\$ 37,18)", 6)
    ))

}
