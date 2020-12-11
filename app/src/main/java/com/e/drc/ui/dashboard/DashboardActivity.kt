package com.e.drc.ui.dashboard

import android.app.AlertDialog
import android.os.Bundle
import android.view.ContextThemeWrapper
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.e.drc.BR
import com.e.drc.R
import com.e.drc.adapter.DashboarAdapater
import com.e.drc.base.BaseActivity
import com.e.drc.databinding.ActivityDashboardBinding

import com.e.drc.extensions.openActivity
import com.e.drc.extensions.showToast
import com.e.drc.model.NewsModel
import com.e.drc.ui.description.DescriptionActivity
import com.e.drc.ui.login.LoginActivity
import com.e.drc.utils.ViewModelProviderFactory
import org.koin.android.ext.android.inject


class DashboardActivity : BaseActivity<ActivityDashboardBinding, DashboardViewModel>(),
    DashboardNavigator {

    private val factory: ViewModelProviderFactory by inject()

    override val viewModel: DashboardViewModel
        get() = ViewModelProviders.of(this, factory).get(DashboardViewModel::class.java)

    private var activityDashboardBinding: ActivityDashboardBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val isStatusBar: Boolean
        get() = true

    override val layoutId: Int
        get() = R.layout.activity_dashboard

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDashboardBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        viewModel.getData(this)
        activityDashboardBinding!!.toolbar.setRightButtonListener(listener = View.OnClickListener {
           viewModel.getSession().logout()
            openActivity(LoginActivity::class.java)
            finish()
        })
    }

    override fun onSuccessData(titleList: List<NewsModel.Article>) {

        viewModel.dashboarAdapater =
            DashboarAdapater(this,this,titleList as  ArrayList<NewsModel.Article>)
        with(activityDashboardBinding!!.recyclerViewHeadList) {

            var linearLayoutManager = LinearLayoutManager(this@DashboardActivity)
            activityDashboardBinding!!.recyclerViewHeadList.layoutManager = linearLayoutManager
            adapter = viewModel.dashboarAdapater

        }
    }

   override fun onClcik(model:NewsModel.Article)
    {
        var bundle=Bundle()
        bundle.putSerializable("model",model as NewsModel.Article)
        openActivity(DescriptionActivity::class.java,bundle)
    }

}