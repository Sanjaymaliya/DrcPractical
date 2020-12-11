package  com.e.drc.ui.login

import com.e.drc.base.BaseNavigator

interface LoginNavigator : BaseNavigator {

    fun onLoginButtonClickHandler()

    fun onAuthFail(errorMassage:String)

    fun onAuthSuccess()
}