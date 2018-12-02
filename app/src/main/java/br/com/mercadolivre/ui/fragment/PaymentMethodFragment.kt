package br.com.mercadolivre.ui.fragment

import android.os.Bundle
import android.view.View
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseFragment
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.PaymentMethodPresenter
import javax.inject.Inject

interface PaymentMethodView : BaseView

class PaymentMethodFragment : BaseFragment(), PaymentMethodView {

    @Inject
    lateinit var presenter: PaymentMethodPresenter

    override fun getLayoutId() = R.layout.tab_payment_method

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewComponent.inject(this)

        presenter.onAttachView(this)
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

}
