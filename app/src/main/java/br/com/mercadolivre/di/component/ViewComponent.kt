package br.com.mercadolivre.di.component

import br.com.mercadolivre.di.module.ViewModule
import br.com.mercadolivre.di.scope.ViewScope
import dagger.Component

@ViewScope
@Component(modules = [ViewModule::class], dependencies = [AppComponent::class])
interface ViewComponent
