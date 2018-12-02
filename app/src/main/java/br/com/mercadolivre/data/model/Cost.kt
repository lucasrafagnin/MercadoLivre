package br.com.mercadolivre.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Cost(
        @Json(name = "recommended_message")
        val message: String,
        val installments: Int
)
