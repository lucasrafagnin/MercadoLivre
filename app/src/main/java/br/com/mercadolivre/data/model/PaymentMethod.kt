package br.com.mercadolivre.data.model

import com.squareup.moshi.Json
import se.ansman.kotshi.JsonSerializable

@JsonSerializable
data class PaymentMethod(
        val id: String,
        val name: String,
        val thumbnail: String,
        @Json(name = "payment_type_id")
        val type: PaymentType
)

enum class PaymentType {
    @Json(name = "credit_card")
    CREDIT,
    @Json(name = "debit_card")
    DEBIT,
    @Json(name = "ticket")
    TICKET
}
