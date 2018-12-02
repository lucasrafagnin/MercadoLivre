package br.com.mercadolivre.data.repository

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.data.datasource.cache.PaymentCache
import br.com.mercadolivre.data.datasource.remote.PaymentCloud
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class PaymentRepositoryTest {

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
    fun testGetPaymentMethods() {
        val mockPaymentCloud = mock<PaymentCloud> {
            on { getPaymentMethods() } doReturn Observable.just(Fixture.paymentMethods)
        }

        val observable = PaymentRepository(mockPaymentCloud, mock())
                .getPaymentMethods()
                .test()
                .assertNoErrors()

        observable.assertValue { it == Fixture.paymentMethods }
    }

    @Test
    fun testGetCardIssuers() {
        val mockPaymentCloud = mock<PaymentCloud> {
            on { getCardIssuers(Fixture.paymentMethodId) } doReturn Observable.just(Fixture.cardIssuers)
        }

        val observable = PaymentRepository(mockPaymentCloud, mock())
                .getCardIssuers(Fixture.paymentMethodId)
                .test()
                .assertNoErrors()

        observable.assertValue { it == Fixture.cardIssuers }
    }

    @Test
    fun testGetInstallments() {
        val mockPaymentCloud = mock<PaymentCloud> {
            on { getInstallments(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId) } doReturn
                    Observable.just(Fixture.installments)
        }

        val observable = PaymentRepository(mockPaymentCloud, mock())
                .getInstallments(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId)
                .test()
                .assertNoErrors()

        observable.assertValue { it == Fixture.installments }
    }

    @Test
    fun testGetPayment() {
        val cache = PaymentCache()
        cache.save(Fixture.payment)

        val payment = PaymentRepository(mock(), cache)
                .getPayment()

        assertThat(payment).isEqualTo(cache.get())
        assertThat(payment).isEqualTo(Fixture.payment)
    }

    @Test
    fun testSavePayment() {
        val cache = PaymentCache()

        PaymentRepository(mock(), cache).savePayment(Fixture.payment)

        assertThat(cache.get()).isEqualTo(Fixture.payment)
    }

    @Test
    fun testMakePayment() {
        val cache = PaymentCache()
        cache.save(Fixture.payment)

        assertThat(cache.get()).isEqualTo(Fixture.payment)
        PaymentRepository(mock(), cache).makePayment()

        assertThat(cache.get()).isNotEqualTo(Fixture.payment)
    }

    @Test
    fun testClearCache() {
        val cache = PaymentCache()
        cache.save(Fixture.payment)

        assertThat(cache.get()).isEqualTo(Fixture.payment)
        PaymentRepository(mock(), cache).clearCache()

        assertThat(cache.get()).isNotEqualTo(Fixture.payment)
    }

}
