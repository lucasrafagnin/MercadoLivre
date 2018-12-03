package br.com.mercadolivre.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import br.com.mercadolivre.R
import br.com.mercadolivre.di.module.GlideApp
import br.com.mercadolivre.presentation.model.CardIssuer
import kotlinx.android.synthetic.main.item_card_issuer.view.*

interface CardIssuerSelected {
    fun itemSelected(cardIssuerId: String)
}

class CardIssuerAdapter(
        private val listener: CardIssuerSelected
) : RecyclerView.Adapter<CardIssuerAdapter.ViewHolder>() {

    var issuerSelected: String? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var items = listOf<CardIssuer>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], issuerSelected, listener)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_card_issuer, parent, false))
    }

    override fun getItemCount() = items.size

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: CardIssuer, issuerSelected: String?, listener: CardIssuerSelected) {
            with(view) {
                GlideApp.with(context)
                        .load(item.thumbnail)
                        .placeholder(AppCompatResources.getDrawable(context, R.drawable.img_placeholder))
                        .into(thumbnail)
                name.text = item.name
                thumbnail.contentDescription = item.name
                setOnClickListener { listener.itemSelected(item.id) }
                selected.visibility = if (issuerSelected == item.id) View.VISIBLE else View.GONE
            }
        }

    }

}
