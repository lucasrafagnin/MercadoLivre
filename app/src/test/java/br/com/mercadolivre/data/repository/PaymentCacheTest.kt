package br.com.mercadolivre.data.repository

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.data.datasource.cache.PaymentCache
import com.google.common.truth.Truth.assertThat
import org.junit.Test

class PaymentCacheTest {

    @Test
    fun testSave() {
        val cache = PaymentCache()
        cache.save(Fixture.payment)

        assertThat(cache.get()).isEqualTo(Fixture.payment)
    }

    @Test
    fun testClear() {
        val cache = PaymentCache()
        cache.save(Fixture.payment)
        cache.clear()

        assertThat(cache.get()).isNotEqualTo(Fixture.payment)
    }

}
