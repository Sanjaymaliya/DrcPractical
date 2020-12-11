package com.e.drc.ui.description

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.e.drc.BR
import com.e.drc.R
import com.e.drc.base.BaseActivity
import com.e.drc.databinding.ActivityDescriptionBinding
import com.e.drc.model.NewsModel
import com.e.drc.utils.ViewModelProviderFactory
import org.koin.android.ext.android.inject


class DescriptionActivity : BaseActivity<ActivityDescriptionBinding, DescriptionViewModel>(),
    DescriptionNavigator {

    private val factory: ViewModelProviderFactory by inject()

    override val viewModel: DescriptionViewModel
        get() = ViewModelProviders.of(this, factory).get(DescriptionViewModel::class.java)

    private var activityDescriptionBinding: ActivityDescriptionBinding? = null

    override val bindingVariable: Int
        get() = BR.viewModel

    override val isFullScreen: Boolean
        get() = false

    override val isStatusBar: Boolean
        get() = true

    override val layoutId: Int
        get() = R.layout.activity_description

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityDescriptionBinding = getViewDataBinding()
        viewModel.setNavigator(this)
        activityDescriptionBinding!!.toolbar.setBackButtonListener(listener = View.OnClickListener {
            onBackPressed()
        })
        intent.extras?.run {
            var model = getSerializable("model") as NewsModel.Article
            activityDescriptionBinding!!.txtTitle.text= model.title
            activityDescriptionBinding!!.txtDate.text= model.publishedAt
            activityDescriptionBinding!!.txtDes.text= model.description
            Glide.with(this@DescriptionActivity)
                .load(model.urlToImage)
                .into(activityDescriptionBinding!!.imgProduct)
        }

    }


    override fun onBackPressed() {
        super.onBackPressed()
    }


}