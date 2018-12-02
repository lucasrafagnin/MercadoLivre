package br.com.mercadolivre.domain.usecase

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.data.repository.PaymentRepository
import br.com.mercadolivre.exception.EmptyStateException
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class GetInstallmentsTest {

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
            on { getInstallments(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId) } doReturn
                    Observable.just(Fixture.installments)
        }

        GetInstallments(mockRepository)
                .execute(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId)
                .test()
                .assertNoErrors()
    }

    @Test
    fun testExecute_empty() {
        val mockRepository = mock<PaymentRepository> {
            on { getInstallments(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId) } doReturn Observable.just(listOf())
        }

        GetInstallments(mockRepository)
                .execute(Fixture.paymentMethodId, Fixture.amount, Fixture.cardIssuerId)
                .test()
                .assertNoValues()
                .assertError { it is EmptyStateException }
    }

}
