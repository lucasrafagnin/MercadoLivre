package br.com.mercadolivre.navigation

import android.content.Intent
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.ui.activity.PaymentActivity
import br.com.mercadolivre.ui.activity.PopupSuccessActivity
import javax.inject.Inject

class NavigationController @Inject constructor(
        private val activity: BaseActivity
) {

    fun launchPayment() = activity.startActivity(Intent(activity, PaymentActivity::class.java))

    fun launchPopupSuccess() = activity.startActivity(Intent(activity, PopupSuccessActivity::class.java))

}
