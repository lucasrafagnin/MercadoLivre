package br.com.mercadolivre.di.component

import br.com.mercadolivre.di.module.ViewModule
import br.com.mercadolivre.di.scope.ViewScope
import br.com.mercadolivre.ui.activity.HomeActivity
import br.com.mercadolivre.ui.fragment.CardIssuerFragment
import br.com.mercadolivre.ui.fragment.InstallmentFragment
import br.com.mercadolivre.ui.fragment.PaymentMethodFragment
import dagger.Component

@ViewScope
@Component(modules = [ViewModule::class], dependencies = [AppComponent::class])
interface ViewComponent {

    // Activities
    fun inject(target: HomeActivity)

    // Fragments
    fun inject(target: PaymentMethodFragment)
    fun inject(target: CardIssuerFragment)
    fun inject(target: InstallmentFragment)

}
