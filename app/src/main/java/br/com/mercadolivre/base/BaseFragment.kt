package br.com.mercadolivre.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import br.com.mercadolivre.di.component.DaggerViewComponent
import br.com.mercadolivre.di.component.ViewComponent
import br.com.mercadolivre.di.module.ViewModule
import br.com.mercadolivre.ext.getApp

abstract class BaseFragment : Fragment(), BaseView {

    open val viewComponent: ViewComponent by lazy {
        DaggerViewComponent.builder()
                .appComponent(getApp().appComponent)
                .viewModule(ViewModule(activity as BaseActivity))
                .build()
    }

    abstract fun getLayoutId(): Int
    override fun showLoading() {}
    override fun showContent() {}
    override fun showConnectionError() {}
    override fun showMessage(message: Int) = Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(getLayoutId(), container, false)
    }

}
