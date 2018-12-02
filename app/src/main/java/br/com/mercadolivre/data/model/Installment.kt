package br.com.mercadolivre.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class Installment(
        @Json(name = "payer_costs")
        val costs: List<Cost>
)
