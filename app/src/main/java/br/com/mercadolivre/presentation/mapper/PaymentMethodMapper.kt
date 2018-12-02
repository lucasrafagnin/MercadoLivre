package br.com.mercadolivre.presentation.mapper

import br.com.mercadolivre.data.model.PaymentMethod
import javax.inject.Inject
import br.com.mercadolivre.presentation.model.PaymentMethod as ViewModel

class PaymentMethodMapper @Inject constructor() {

    fun map(items: List<PaymentMethod>) = items.map {
        with(it) {
            ViewModel(id, name, thumbnail)
        }
    }

}
