package br.com.mercadolivre.presentation.mapper

import br.com.mercadolivre.data.model.CardIssuer
import javax.inject.Inject
import br.com.mercadolivre.presentation.model.CardIssuer as ViewModel

class CardIssuerMapper @Inject constructor() {

    fun map(items: List<CardIssuer>) = items.map {
        with(it) {
            ViewModel(id, name, thumbnail)
        }
    }

}
