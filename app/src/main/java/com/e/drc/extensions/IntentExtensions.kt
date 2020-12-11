package com.e.drc.extensions

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

fun <T> Context.openActivity(it: Class<T>, bundle: Bundle) {
    val intent = Intent(this, it)
    intent.putExtras(bundle)
    startActivity(intent)
}
