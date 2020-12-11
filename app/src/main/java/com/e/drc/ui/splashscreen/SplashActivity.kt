package com.e.drc.ui.splashscreen

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.e.drc.BR
import com.e.drc.R
import com.e.drc.base.BaseActivity
import com.e.drc.databinding.ActivitySplashBinding
import com.e.drc.extensions.openActivity
import com.e.drc.extensions.showToast
import com.e.drc.ui.dashboard.DashboardActivity
import com.e.drc.ui.login.LoginActivity
import com.e.drc.utils.Session.Key.IS_LOGIN
import com.e.drc.utils.ViewModelProviderFactory
import org.koin.android.ext.android.inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>(), SplashNavigator {

    private val factory: ViewModelProviderFactory by inject()

    override val viewModel: SplashViewModel
        get() = ViewModelProviders.of(this, factory).get(SplashViewModel::class.java)

    private var activityLoginBinding: ActivitySplashBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val isStatusBar: Boolean
        get() = true

    override val layoutId: Int
        get() = R.layout.activity_splash

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        viewModel.onSplashHandler()

    }

    override fun onMainScreen() {
        showToast(""+viewModel.getSession().getLogin(IS_LOGIN))
        if (viewModel.getSession().getLogin(IS_LOGIN)) {
            var buddle=Bundle()
            buddle.putString("Flag","0")
            openActivity(DashboardActivity::class.java,buddle)
            finish()
        } else {
            openActivity(LoginActivity::class.java)
            finish()
        }

    }

}
