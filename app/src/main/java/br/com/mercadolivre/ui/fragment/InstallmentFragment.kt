package br.com.mercadolivre.ui.fragment

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseFragment
import br.com.mercadolivre.base.BaseView
import br.com.mercadolivre.ext.hide
import br.com.mercadolivre.ext.show
import br.com.mercadolivre.presentation.adapter.InstallmentAdapter
import br.com.mercadolivre.presentation.adapter.InstallmentSelected
import br.com.mercadolivre.presentation.model.Installment
import br.com.mercadolivre.presentation.presenter.InstallmentPresenter
import br.com.mercadolivre.ui.activity.PaymentView
import kotlinx.android.synthetic.main.tab_content.*
import javax.inject.Inject

interface InstallmentView : BaseView, InstallmentSelected {
    fun setInstallmentSelected(installment: Int?)
    fun showItems(items: List<Installment>)
}

class InstallmentFragment : BaseFragment(), InstallmentView {

    private lateinit var paymentListener: PaymentView

    @Inject
    lateinit var presenter: InstallmentPresenter

    private lateinit var adapter: InstallmentAdapter

    override fun getLayoutId() = R.layout.tab_content

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewComponent.inject(this)

        adapter = InstallmentAdapter(this)
        list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)

        presenter.onAttachView(this)
        presenter.onStart()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PaymentView) paymentListener = context else throw ClassCastException()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetachView()
    }

    override fun setInstallmentSelected(installment: Int?) {
        adapter.installmentSelected = installment
    }

    override fun itemSelected(installment: Int) {
        setInstallmentSelected(installment)
        presenter.selectItem(installment)
        paymentListener.enableNext(true)
    }

    override fun showItems(items: List<Installment>) {
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
