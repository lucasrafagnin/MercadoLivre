package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.data.datasource.cache.PaymentCache
import br.com.mercadolivre.data.repository.PaymentRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class SavePaymentCacheTest {

    @Before
    fun setUp() {
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }
        RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }
    }

    @After
    fun tearDown() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testExecute() {
        val cache = PaymentCache()

        SavePaymentCache(PaymentRepository(mock(), cache)).execute(
                Fixture.amount, Fixture.amountFormatted, Fixture.paymentMethodId, Fixture.cardIssuerId, Fixture.installmentsId
        )

        assertThat(cache.get().amount).isEqualTo(Fixture.amount)
        assertThat(cache.get().paymentMethodId).isEqualTo(Fixture.paymentMethodId)
        assertThat(cache.get().cardIssuerId).isEqualTo(Fixture.cardIssuerId)
        assertThat(cache.get().installments).isEqualTo(Fixture.installmentsId)
    }

}
