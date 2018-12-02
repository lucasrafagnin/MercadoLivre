package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.HomePresenter
import kotlinx.android.synthetic.main.home.*
import javax.inject.Inject

interface HomeView : BaseView

class HomeActivity : BaseActivity(), HomeView {

    @Inject
    lateinit var presenter: HomePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)
        viewComponent.inject(this)

        action.setOnClickListener {}

        presenter.onAttachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

}
