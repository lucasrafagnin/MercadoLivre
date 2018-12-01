package br.com.mercadolivre.di.module

import android.content.Context
import br.com.mercadolivre.App
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(
        private val context: App
) {

    @Provides
    @Singleton
    fun provideContext(): Context = context

}
