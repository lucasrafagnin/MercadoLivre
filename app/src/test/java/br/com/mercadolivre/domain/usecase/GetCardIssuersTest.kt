package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.data.repository.PaymentRepository
import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import br.com.mercadolivre.exception.EmptyStateException
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetCardIssuersTest {

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
    fun testExecute_success() {
        val mockRepository = mock<PaymentRepository> {
            on { getCardIssuers(Fixture.paymentMethodId) } doReturn Observable.just(Fixture.cardIssuers)
        }

        val values = GetCardIssuers(mockRepository)
                .execute(Fixture.paymentMethodId)
                .test()
                .assertNoErrors()
                .values()
        assertThat(values).hasSize(1)

        val listResult = values[0]
        assertThat(listResult).hasSize(3)
    }

    @Test
    fun testExecute_empty() {
        val mockRepository = mock<PaymentRepository> {
            on { getCardIssuers(Fixture.paymentMethodId) } doReturn Observable.empty()
        }

        GetCardIssuers(mockRepository)
                .execute(Fixture.paymentMethodId)
                .test()
                .assertNoValues()
                .assertError { it is EmptyStateException }
    }

}
