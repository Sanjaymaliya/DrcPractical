package com.e.drc.ui.login


import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.e.drc.BR
import com.e.drc.R
import com.e.drc.base.BaseActivity
import com.e.drc.databinding.ActivityLoginBinding
import com.e.drc.extensions.openActivity
import com.e.drc.extensions.showToast
import com.e.drc.model.UserModel
import com.e.drc.ui.dashboard.DashboardActivity
import com.e.drc.utils.RealmController
import com.e.drc.utils.Session.Key.IS_LOGIN
import com.e.drc.utils.Validation
import com.e.drc.utils.ViewModelProviderFactory
import io.realm.Realm
import org.koin.android.ext.android.inject


class LoginActivity : BaseActivity<ActivityLoginBinding, LoginViewModel>(), LoginNavigator {

    private val factory: ViewModelProviderFactory by inject()

    override val viewModel: LoginViewModel
        get() = ViewModelProviders.of(this, factory).get(LoginViewModel::class.java)

    private var activityLoginBinding: ActivityLoginBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val isStatusBar: Boolean
        get() = true

    override val layoutId: Int
        get() = R.layout.activity_login

    var realm: Realm? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityLoginBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        realm = RealmController.with().realm

    }
    override fun onAuthFail(errorMassage: String) {
        showToast(errorMassage)
    }

    override fun onAuthSuccess() {

    }

    override fun onLoginButtonClickHandler() {
        if(isValid())
        {
            val data = realm?.where(UserModel::class.java)
                ?.equalTo("firstName", activityLoginBinding!!.editTextUserNme.text.toString().trim())
                ?.equalTo("email", activityLoginBinding!!.editTextEmail.text.toString().trim())
                ?.equalTo("password", activityLoginBinding!!.editTextPassword.text.toString().trim())?.findAll()

            if(data!!.size>0)
            {
                viewModel.getSession().setName(activityLoginBinding!!.editTextUserNme.text.toString().trim())
                showToast("Login Success")

            }
            else{
                add()
            }
            viewModel.getSession().setString(IS_LOGIN,true)

            openActivity(DashboardActivity::class.java)
            finish()
        }

    }
    private fun isValid(): Boolean {
        val email = Validation.isEmailValid(
            this, activityLoginBinding!!.editTextEmail.text.toString().trim(), activityLoginBinding!!.textInputEmail
        )
        val password = Validation.isPasswordValid(
            this, activityLoginBinding!!.editTextPassword.text.toString().trim(), activityLoginBinding!!.textInputPassword
        )
        return email && password
    }

    fun add()
    {
        val userModel = UserModel()
        userModel.firstName=activityLoginBinding!!.editTextUserNme.text.toString().trim()
        userModel.email=activityLoginBinding!!.editTextEmail.text.toString().trim()
        userModel.password=activityLoginBinding!!.editTextPassword.text.toString().trim()
        realm!!.beginTransaction()
        realm!!.copyToRealm(userModel)
        realm!!.commitTransaction()
        viewModel.getSession().setName(activityLoginBinding!!.editTextUserNme.text.toString().trim())

        showToast("Register Successfully")
    }
}
