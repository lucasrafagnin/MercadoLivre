package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.model.PaymentMethod
import br.com.mercadolivre.data.model.PaymentType
import br.com.mercadolivre.data.repository.PaymentRepository
import io.reactivex.Single
import mobi.porquenao.sovai.exception.EmptyStateException
import javax.inject.Inject

class GetPaymentMethods @Inject constructor(
        private val repository: PaymentRepository
) {

    fun execute(): Single<MutableList<PaymentMethod>> =
            repository.getPaymentMethods()
                    .flatMapIterable { it }
                    .filter { it.type == PaymentType.CREDIT }
                    .switchIfEmpty { it.onError(EmptyStateException()) }
                    .toList()

}
