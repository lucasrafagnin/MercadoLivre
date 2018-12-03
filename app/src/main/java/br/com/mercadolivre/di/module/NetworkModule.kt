package br.com.mercadolivre.di.module

import android.content.Context
import android.os.Build
import br.com.mercadolivre.BuildConfig
import br.com.mercadolivre.data.repository.security.TLSSocketFactory
import br.com.mercadolivre.data.repository.security.TLSX509TrustManager
import br.com.mercadolivre.factory.AdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.SSLContext
import javax.net.ssl.SSLSocketFactory


@Module
class NetworkModule(
        private val context: Context
) {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder()
            .add(AdapterFactory.INSTANCE)
            .build()

    @Provides
    @Singleton
    fun provideSSLSocketFactory(): SSLSocketFactory {
        val sslContext: SSLContext
        try {
            sslContext = SSLContext.getInstance("TLS")
        } catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }

        try {
            sslContext.init(null, null, null)
        } catch (e: KeyManagementException) {
            throw RuntimeException(e)
        }
        return if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT) {
            TLSSocketFactory(sslContext.socketFactory)
        } else {
            sslContext.socketFactory
        }
    }

    @Provides
    @Singleton
    fun provideHttpClient(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }

    @Provides
    @Singleton
    fun provideCache(): Cache {
        val cacheSize = (5 * 1024 * 1024).toLong() // 5 MB
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(cache: Cache, sslSocketFactory: SSLSocketFactory): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                with(chain.request()) {
                    val originalHttpUrl = url()
                    val url = originalHttpUrl.newBuilder()
                            .addQueryParameter("public_key", BuildConfig.PUBLIC_KEY)
                            .build()

                    val requestBuilder = newBuilder().url(url)
                    val request = requestBuilder.build()
                    chain.proceed(request)
                }
            }
            .sslSocketFactory(sslSocketFactory, TLSX509TrustManager())
            .cache(cache)
            .connectTimeout(BuildConfig.CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
            .readTimeout(BuildConfig.READ_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
            .build()

}
