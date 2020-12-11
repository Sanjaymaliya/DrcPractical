package com.e.drc.utils

import android.content.Context
import com.e.drc.R
import com.google.android.material.textfield.TextInputLayout
import java.util.regex.Pattern

object Validation {


    fun isValidEmail(str: String): Boolean {
        return Pattern.compile("^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?$")
            .matcher(str).matches()
    }


    public fun isPasswordValid(str: String): Boolean {
        return Pattern.compile("(?=.*[0-9])(?=.*[A-Z]).{6,}\$").matcher(str).matches()
    }
    fun isEmailValid(context: Context, email: String, textInputLayout: TextInputLayout): Boolean {

        when {
            email.isEmpty() -> {
                textInputLayout!!.isErrorEnabled = true
                textInputLayout!!.error = context.getString(R.string.email_empty)
                return false
            }
            !isValidEmail(email) -> {
                textInputLayout!!.isErrorEnabled = true
                textInputLayout!!.error = context.getString(R.string.enter_valid_email)
                return false
            }
            else -> {
                textInputLayout!!.isErrorEnabled = false
            }
        }

        return true
    }


    fun isPasswordValid(context: Context, password: String, textInputLayout: TextInputLayout): Boolean {

        when {
            password.isEmpty() -> {
                textInputLayout!!.isErrorEnabled = true
                textInputLayout!!.error = context.getString(R.string.password_empty)
                return false
            }
            !isPasswordValid(password) -> {
                textInputLayout!!.isErrorEnabled = true
                textInputLayout!!.error = context.getString(R.string.password_must_be_at_least_characters)
                return false
            }
            else -> {
                textInputLayout!!.isErrorEnabled = false
            }
        }

        return true
    }


}