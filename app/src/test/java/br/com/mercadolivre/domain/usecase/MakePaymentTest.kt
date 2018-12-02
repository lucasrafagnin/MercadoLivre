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

class MakePaymentTest {

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
        cache.save(Fixture.payment)

        MakePayment(PaymentRepository(mock(), cache)).execute()

        assertThat(cache).isNotEqualTo(Fixture.payment)
    }

}
