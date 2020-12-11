package com.e.drc.ui.login

import android.app.Application
import android.content.Context
import com.e.drc.R
import com.e.drc.base.BaseViewModel
import com.e.drc.utils.Session

class LoginViewModel(application: Application, session: Session) :
    BaseViewModel<LoginNavigator>(application, session) {

    fun onLoginButtonClick()
    {
        getNavigator()?.onLoginButtonClickHandler()
    }
  }