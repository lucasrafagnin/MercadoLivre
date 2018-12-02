package br.com.mercadolivre.di.module

import br.com.mercadolivre.base.BaseActivity
import dagger.Module
import dagger.Provides

@Module
class ViewModule(
        private val activity: BaseActivity
) {

    @Provides
    fun provideActivity() = activity

}
