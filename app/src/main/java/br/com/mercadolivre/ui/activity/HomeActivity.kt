package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.presentation.presenter.HomePresenter
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.android.schedulers.AndroidSchedulers
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

        action.setOnClickListener {
            presenter.savePrice(price.currencyDouble, price.currencyText)
            presenter.launchPaymentMethod()
        }

        presenter.onAttachView(this)
        presenter.addDisposable(RxTextView.textChanges(price)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { enableNext(!price.currencyText.isEmpty() && price.currencyDouble > 0) })
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    private fun enableNext(enabled: Boolean) {
        action.isEnabled = enabled
    }

}
