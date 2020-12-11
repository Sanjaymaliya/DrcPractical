package com.e.drc.ui.dashboard

import android.app.Activity
import android.app.Application
import android.content.Context
import android.view.View
import com.e.drc.adapter.DashboarAdapater
import com.e.drc.base.BaseViewModel
import com.e.drc.extensions.NetworkUtils
import com.e.drc.model.NewsModel
import com.e.drc.restapi.ApiInitialize
import com.e.drc.restapi.ApiRequest
import com.e.drc.restapi.ApiResponseInterface
import com.e.drc.restapi.ApiResponseManager

import com.e.drc.utils.Session

class DashboardViewModel(application: Application, session: Session) :
    BaseViewModel<DashboardNavigator>(application, session), ApiResponseInterface {

    lateinit var dashboarAdapater: DashboarAdapater

    fun getData(context: Activity) {
        if (NetworkUtils.isNetworkAvailable(context)) {
            ApiRequest(context, ApiInitialize.initializes()
                .getData(),
                100, true, this)

        }
    }

    override fun getApiResponse(apiResponseManager: ApiResponseManager<*>) {
        if (apiResponseManager.type ===100) run {
            val mModel = apiResponseManager.response as NewsModel
            getNavigator()?.onSuccessData(mModel!!.articles!!)
        }
    }
}