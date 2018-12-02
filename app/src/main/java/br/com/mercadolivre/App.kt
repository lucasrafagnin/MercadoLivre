package br.com.mercadolivre

import android.app.Application
import br.com.mercadolivre.di.component.AppComponent
import br.com.mercadolivre.di.component.DaggerAppComponent
import br.com.mercadolivre.di.module.AppModule
import br.com.mercadolivre.di.module.DataSourceModule
import br.com.mercadolivre.di.module.NetworkModule
import timber.log.Timber

class App : Application() {

    val appComponent: AppComponent by lazy {
        DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkModule(NetworkModule(this))
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }

}
