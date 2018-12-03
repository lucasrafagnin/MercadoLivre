package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.PopupSuccessPresenter
import kotlinx.android.synthetic.main.popup_success.*
import javax.inject.Inject

interface PopupSuccessView : BaseView {
    fun setDescription(text: String)
}

class PopupSuccessActivity : BaseActivity(), PopupSuccessView {

    @Inject
    lateinit var presenter: PopupSuccessPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_success)
        viewComponent.inject(this)

        action.setOnClickListener { finish() }

        presenter.onAttachView(this)
        presenter.onStart()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.clearCache()
        presenter.onDetachView()
    }

    override fun setDescription(text: String) {
        description.text = text
    }

}
