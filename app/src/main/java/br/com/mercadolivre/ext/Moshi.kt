package br.com.mercadolivre.ext

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import timber.log.Timber

inline fun <reified T> Moshi.fromJson(json: String) = getAdapter<T>().fromJson(json)
        ?: throw IllegalJsonException().apply {
            Timber.w("IllegalJsonException when trying to convert the json %s into %s", json, T::class.java)
        }

inline fun <reified T> Moshi.toJson(value: T) = getAdapter<T>().toJson(value) ?: "{}".apply {
    Timber.w("Invalid value: %s to adapter %s, returning the default json: %s.", value, getAdapter<T>(), this)
}

inline fun <reified T> Moshi.getAdapter(): JsonAdapter<T> = adapter<T>(T::class.java)

class IllegalJsonException : IllegalStateException()
