package com.e.drc.ui.dashboard
import com.e.drc.base.BaseNavigator
import com.e.drc.model.NewsModel


interface DashboardNavigator : BaseNavigator {

    fun onSuccessData(titleList: List<NewsModel.Article>)

    fun onClcik(model:NewsModel.Article)

}