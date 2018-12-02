package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.PaymentPresenter
import javax.inject.Inject

interface PaymentView : BaseView

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

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

}
