package br.com.mercadolivre.ui.fragment

import android.os.Bundle
import android.view.View
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseFragment
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.InstallmentPresenter
import javax.inject.Inject

interface InstallmentView : BaseView

class InstallmentFragment : BaseFragment(), InstallmentView {

    @Inject
    lateinit var presenter: InstallmentPresenter

    override fun getLayoutId() = R.layout.tab_content

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
