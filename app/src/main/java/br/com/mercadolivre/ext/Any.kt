package br.com.mercadolivre.ext

import android.content.Context
import android.view.View
import androidx.fragment.app.Fragment
import br.com.mercadolivre.App

fun Any.getApp() = when (this) {
    is Context -> applicationContext as App
    is Fragment -> context!!.applicationContext as App
    else -> throw IllegalStateException()
}

fun Any.show(vararg views: View) {
    views.forEach { it.visibility = View.VISIBLE }
}

fun Any.hide(vararg views: View) {
    views.forEach { it.visibility = View.GONE }
}
