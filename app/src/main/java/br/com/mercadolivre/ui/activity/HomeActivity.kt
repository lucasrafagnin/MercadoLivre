package br.com.mercadolivre.ui.activity

import android.os.Bundle
import br.com.mercadolivre.R
import br.com.mercadolivre.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home)

        viewComponent.inject(this)
    }

}
