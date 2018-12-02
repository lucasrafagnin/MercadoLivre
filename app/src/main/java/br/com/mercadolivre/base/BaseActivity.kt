package br.com.mercadolivre.base

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.com.mercadolivre.di.component.DaggerViewComponent
import br.com.mercadolivre.di.component.ViewComponent
import br.com.mercadolivre.di.module.ViewModule
import br.com.mercadolivre.ext.getApp

abstract class BaseActivity : AppCompatActivity(), BaseView {

    open val viewComponent: ViewComponent by lazy {
        DaggerViewComponent.builder()
                .appComponent(getApp().appComponent)
                .viewModule(ViewModule(this))
                .build()
    }

    override fun showLoading() {}
    override fun showContent() {}
    override fun showError() {}
    override fun showEmpty() {}
    override fun showMessage(message: Int) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

}