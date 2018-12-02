package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.PaymentPresenter
import br.com.mercadolivre.ui.fragment.CardIssuerFragment
import br.com.mercadolivre.ui.fragment.InstallmentFragment
import br.com.mercadolivre.ui.fragment.PaymentMethodFragment
import kotlinx.android.synthetic.main.payment.*
import javax.inject.Inject

interface PaymentView : BaseView {
    fun chooseTab(currentPosition: Int, nextPosition: Int)
    fun close()
    fun enableNext(enabled: Boolean)
}

class PaymentActivity : BaseActivity(), PaymentView {

    @Inject
    lateinit var presenter: PaymentPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.payment)
        viewComponent.inject(this)

        presenter.onAttachView(this)
        presenter.onStart()
    }

    override fun onBackPressed() {
        presenter.backPage()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun enableNext(enabled: Boolean) {
        action.isEnabled = enabled
    }

    override fun close() = finish()

    override fun chooseTab(currentPosition: Int, nextPosition: Int) {
        val goForward = nextPosition > currentPosition
        step_view.go(nextPosition, true)
        val fragment = when (nextPosition) {
            0 -> {
                action.setText(R.string.payment_method_next)
                PaymentMethodFragment()
            }
            1 -> {
                action.setText(R.string.card_issuer_next)
                CardIssuerFragment()
            }
            2 -> {
                action.setText(R.string.installment_next)
                InstallmentFragment()
            }
            else -> throw IllegalStateException()
        }
        with(supportFragmentManager.beginTransaction()) {
            replace(R.id.content, fragment)
            commit()
        }
        toolbar.title = resources.getStringArray(R.array.tab_steps)[nextPosition]
    }

}
