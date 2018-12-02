package br.com.mercadolivre.ext

import android.content.Context
import androidx.fragment.app.Fragment
import br.com.mercadolivre.App

fun Any.getApp() = when (this) {
    is Context -> applicationContext as App
    is Fragment -> context!!.applicationContext as App
    else -> throw IllegalStateException()
}
