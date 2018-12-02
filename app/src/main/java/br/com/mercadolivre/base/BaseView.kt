package br.com.mercadolivre.base

import androidx.annotation.StringRes

interface BaseView {

    fun showLoading()
    fun showContent()
    fun showConnectionError()
    fun showMessage(@StringRes message: Int)

}
