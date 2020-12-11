package com.e.drc.ui.splashscreen

import android.app.Application
import android.os.Handler
import com.e.drc.base.BaseViewModel
import com.e.drc.utils.Session


class SplashViewModel(application: Application, session: Session) : BaseViewModel<SplashNavigator>(application,  session) {

    fun onSplashHandler() {
        Handler().postDelayed({
            getNavigator()!!.onMainScreen()
        }, 2000)
    }


}