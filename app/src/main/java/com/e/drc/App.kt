package com.e.drc

import android.app.Activity
import android.app.Application
import android.os.Bundle
import com.e.drc.koin.networkModule
import com.e.drc.koin.viewModule
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class App : Application(), Application.ActivityLifecycleCallbacks {

    init {
        instance = this

    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        Realm.init(this)
        val config = RealmConfiguration.Builder().name("myrealm.realm").build()
        Realm.setDefaultConfiguration(config)
        val listOfModule = arrayListOf(networkModule, viewModule)

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(listOfModule)
        }

        registerActivityLifecycleCallbacks(this)

    }
    override fun onActivityPaused(activity: Activity?) {

    }

    override fun onActivityResumed(activity: Activity?) {
    }

    override fun onActivityStarted(activity: Activity?) {

    }

    override fun onActivityDestroyed(activity: Activity?) {

    }

    override fun onActivitySaveInstanceState(activity: Activity?, outState: Bundle?) {

    }

    override fun onActivityStopped(activity: Activity?) {

    }

    override fun onActivityCreated(activity: Activity?, savedInstanceState: Bundle?) {
    }
}
