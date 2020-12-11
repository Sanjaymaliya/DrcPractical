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
    fun getLogin(value:String): Boolean {
        return pref.getBoolean(value, false)
    }

    fun setString(key: String, value: Boolean) {
        val editor = pref.edit()
        editor.putBoolean(key, value)
        editor.apply()
    }
    fun setName(value: String) {
        val editor = pref.edit()
        editor.putString("Name", value)
        editor.apply()
    }
    fun getName(): String {
        return pref.getString("Name", "").toString()
    }
    object Key {
        internal const val IS_LOGIN = "isLogin"
    }
}