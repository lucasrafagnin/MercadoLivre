package br.com.mercadolivre.di.module

import br.com.mercadolivre.data.datasource.remote.PaymentCloud
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class DataSourceModule {

    @Provides
    fun providePaymentCloud(retrofit: Retrofit): PaymentCloud =
            retrofit.create<PaymentCloud>(PaymentCloud::class.java)

}
