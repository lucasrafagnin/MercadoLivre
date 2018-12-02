package br.com.mercadolivre.di.component

import android.content.Context
import br.com.mercadolivre.App
import br.com.mercadolivre.data.datasource.remote.PaymentCloud
import br.com.mercadolivre.di.module.AppModule
import br.com.mercadolivre.di.module.DataSourceModule
import br.com.mercadolivre.di.module.NetworkModule
import com.squareup.moshi.Moshi
import dagger.Component
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataSourceModule::class, NetworkModule::class])
interface AppComponent {

    fun inject(target: App)

    // App
    val context: Context

    // Data Source
    val paymentCloud: PaymentCloud

    // External
    val moshi: Moshi
    val okHttpClient: OkHttpClient
    val retrofit: Retrofit

}
