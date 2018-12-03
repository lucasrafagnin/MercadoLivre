package br.com.mercadolivre.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.R
import br.com.mercadolivre.di.module.GlideApp
import br.com.mercadolivre.presentation.model.PaymentMethod
import kotlinx.android.synthetic.main.item_payment_method.view.*

interface PaymentMethodSelected {
    fun itemSelected(methodId: String)
}

class PaymentMethodAdapter(
        private val listener: PaymentMethodSelected
) : RecyclerView.Adapter<PaymentMethodAdapter.ViewHolder>() {

    var methodSelected: String? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var items = listOf<PaymentMethod>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], methodSelected, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_payment_method, parent, false))
    }

    override fun getItemCount() = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: PaymentMethod, methodSelected: String?, listener: PaymentMethodSelected) {
            with(view) {
                GlideApp.with(context)
                        .load(item.thumbnail)
                        .placeholder(AppCompatResources.getDrawable(context, R.drawable.img_placeholder))
                        .into(thumbnail)
                name.text = item.name
                setOnClickListener { listener.itemSelected(item.id) }
                selected.visibility = if (methodSelected == item.id) View.VISIBLE else View.GONE
            }
        }

    }

}
