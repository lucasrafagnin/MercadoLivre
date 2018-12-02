package br.com.mercadolivre.data.model

import br.com.mercadolivre.data.Fixture
import br.com.mercadolivre.ext.fromJson
import br.com.mercadolivre.ext.toJson
import br.com.mercadolivre.factory.AdapterFactory
import br.com.mercadolivre.testExt.readJson
import com.squareup.moshi.Moshi
import org.junit.Test
import org.skyscreamer.jsonassert.JSONAssert

class CardIssuerTest {

    private val moshi = Moshi.Builder()
            .add(AdapterFactory.INSTANCE)
            .build()!!

    @Test
    fun testParse() = br.com.mercadolivre.testExt.assert(moshi.fromJson<CardIssuer>(readJson("card_issuer")), Fixture.cardIssuer)

    @Test
    fun testSerialize() = JSONAssert.assertEquals(moshi.toJson(Fixture.cardIssuer), readJson("card_issuer"), false)

}
