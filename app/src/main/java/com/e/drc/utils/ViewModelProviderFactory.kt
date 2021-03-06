package com.e.drc.utils

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.e.drc.ui.dashboard.DashboardViewModel
import com.e.drc.ui.description.DescriptionViewModel
import com.e.drc.ui.login.LoginViewModel
import com.e.drc.ui.splashscreen.SplashViewModel


class ViewModelProviderFactory(application: Application, session: Session) :
    ViewModelProvider.Factory {

    private var application: Application = application

    private var session: Session = session


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SplashViewModel::class.java) -> SplashViewModel(
                application,
                session
            ) as T
            modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(
                application,
                session
            ) as T
            modelClass.isAssignableFrom(DashboardViewModel::class.java) -> DashboardViewModel(
                application,
                session
            ) as T
            modelClass.isAssignableFrom(DescriptionViewModel::class.java) -> DescriptionViewModel(
                application,
                session
            ) as T

            else -> throw IllegalArgumentException("Unknown class name")
        }
    }
}