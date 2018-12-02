package br.com.mercadolivre.data.model

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.ext.fromJson
import br.com.mercadolivre.ext.toJson
import br.com.mercadolivre.factory.AdapterFactory
import br.com.mercadolivre.testExt.readJson
import com.squareup.moshi.Moshi
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert

class PaymentMethodTest {

    private val moshi = Moshi.Builder()
            .add(AdapterFactory.INSTANCE)
            .build()!!

    @Test
    fun testParse() = br.com.mercadolivre.testExt.assert(moshi.fromJson<PaymentMethod>(readJson("payment_method")), Fixture.paymentMethod)

    @Test
    fun testSerialize() = JSONAssert.assertEquals(moshi.toJson(Fixture.paymentMethod), readJson("payment_method"), false)

}
