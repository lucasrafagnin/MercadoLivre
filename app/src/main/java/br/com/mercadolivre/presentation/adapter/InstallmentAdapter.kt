package br.com.mercadolivre.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.R
import br.com.mercadolivre.presentation.model.Installment
import kotlinx.android.synthetic.main.item_installment.view.*

interface InstallmentSelected {
    fun itemSelected(installment: Int)
}

class InstallmentAdapter(
        private val listener: InstallmentSelected
) : RecyclerView.Adapter<InstallmentAdapter.ViewHolder>() {

    var installmentSelected: Int? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var items = listOf<Installment>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], installmentSelected, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_installment, parent, false))
    }

    override fun getItemCount() = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Installment, installmentSelected: Int?, listener: InstallmentSelected) {
            with(view) {
                installments.text = item.installments
                name.text = item.message
                setOnClickListener { listener.itemSelected(item.id) }
                selected.visibility = if (installmentSelected == item.id) View.VISIBLE else View.GONE
            }
        }

    }

}
