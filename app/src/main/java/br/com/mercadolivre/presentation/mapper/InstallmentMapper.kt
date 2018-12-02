package br.com.mercadolivre.presentation.mapper

import android.content.Context
import br.com.mercadolivre.R
import br.com.mercadolivre.data.model.Installment
import javax.inject.Inject
import br.com.mercadolivre.presentation.model.Installment as ViewModel

class InstallmentMapper @Inject constructor(
        private val context: Context
) {

    fun map(installment: Installment) = installment.costs.map {
        with(it) {
            ViewModel(installments, message, context.getString(R.string.installment_name, installments))
        }
    }

}
