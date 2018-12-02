package br.com.mercadolivre.data.model

import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class CardIssuer(
        val id: String,
        val name: String,
        val thumbnail: String
)
