package com.e.drc.utils

import android.content.Context
import android.content.SharedPreferences
class Session(context: Context) {

    private val prefName = "drc_pre"
    private val privateMode = 0
    private val pref: SharedPreferences
    private val editor: SharedPreferences.Editor
    private val context: Context = context

    init {
        pref = context.getSharedPreferences(prefName, privateMode)
        editor = pref.edit()
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }
    fun getString(value:String): String {
        return pref.getString(value, "")!!
    }

    fun setString(key: String, value: String) {
        val editor = pref.edit()
        editor.putString(key, value)
        editor.apply()
    }

    object Key {
        internal const val IS_LOGIN = "isLogin"
    }
}