package com.e.drc.extensions

import android.app.ProgressDialog
import android.content.Context
import android.text.format.DateFormat
import android.view.View
import android.view.View.*
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import java.text.ParseException

import java.text.SimpleDateFormat
import java.util.*

/**
 * Sets the View's visibility to [View.GONE]
 */
fun View.gone() {
    this.visibility = GONE
}


/**
 * Sets the View's visibility to [View.VISIBLE]
 */
fun View.visible() {
    this.visibility = VISIBLE
}


fun getFormatDate(strDate: String): String {
    if (strDate.isNotEmpty()) {

        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
        var date: Date? = null
        try {
            date = format.parse(strDate)
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        // "MM-dd-yyyy"
        return DateFormat.format("dd-MM-yyyy", date) as String

    }
    return ""
}


fun getProgressDialog(context: Context): ProgressDialog {
    val myCustomProgressDialog = MyCustomProgressDialog(context)
    myCustomProgressDialog.isIndeterminate = true
    myCustomProgressDialog.setCancelable(false)
    myCustomProgressDialog.show()
    return myCustomProgressDialog

}

fun dismissDialog(context: Context, mProgressDialog: ProgressDialog) {
    try {
        if (mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    } catch (e: Exception) {

    }

}

