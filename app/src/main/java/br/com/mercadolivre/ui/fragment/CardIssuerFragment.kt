package br.com.mercadolivre.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseFragment
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.ext.hide
import br.com.mercadolivre.ext.show
import br.com.mercadolivre.presentation.adapter.CardIssuerAdapter
import br.com.mercadolivre.presentation.adapter.CardIssuerSelected
import br.com.mercadolivre.presentation.model.CardIssuer
import br.com.mercadolivre.presentation.presenter.CardIssuerPresenter
import br.com.mercadolivre.ui.activity.PaymentView
import kotlinx.android.synthetic.main.error.*
import kotlinx.android.synthetic.main.tab_content.*
import javax.inject.Inject

interface CardIssuerView : BaseView, CardIssuerSelected {
    fun setIssuerSelected(issuerId: String?)
    fun showItems(items: List<CardIssuer>)
}

class CardIssuerFragment : BaseFragment(), CardIssuerView {

    private lateinit var paymentListener: PaymentView

    @Inject
    lateinit var presenter: CardIssuerPresenter

    private lateinit var adapter: CardIssuerAdapter

    override fun getLayoutId() = R.layout.tab_content

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewComponent.inject(this)

        adapter = CardIssuerAdapter(this)
        list.layoutManager = GridLayoutManager(context, 2)
        retry.setOnClickListener { presenter.onStart() }

        presenter.onAttachView(this)
        presenter.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PaymentView) paymentListener = context else throw ClassCastException()
    }

    override fun onDetach() {
        super.onDetach()
        presenter.onDetachView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun setIssuerSelected(issuerId: String?) {
        adapter.issuerSelected = issuerId
    }

    override fun itemSelected(cardIssuerId: String) {
        setIssuerSelected(cardIssuerId)
        presenter.selectItem(cardIssuerId)
        paymentListener.enableNext(true)
    }

    override fun showItems(items: List<CardIssuer>) {
        hide(error, load, empty)
        show(list)

        list.adapter = adapter
        adapter.items = items
    }

    override fun showEmpty() {
        hide(list, load, error)
        show(empty)
    }

    override fun showLoading() {
        hide(list, empty, error)
        show(load)
    }

    override fun showError() {
        hide(list, load, empty)
        show(error)
    }

}
