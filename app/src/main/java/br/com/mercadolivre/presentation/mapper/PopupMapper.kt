package br.com.mercadolivre.presentation.mapper

import android.content.Context
import br.com.mercadolivre.R
import br.com.mercadolivre.data.model.Payment
import javax.inject.Inject
import br.com.mercadolivre.presentation.model.CardIssuer as ViewModel

class PopupMapper @Inject constructor(
        private val context: Context
) {

    fun map(payment: Payment): String = with(payment) {
        context.getString(R.string.popup_description, amountFormatted, paymentMethodId, cardIssuerId, installments)
    }

}
