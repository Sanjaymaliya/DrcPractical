package com.e.drc.restapi

import com.google.gson.annotations.SerializedName


public class ApiError {
    @SerializedName("status")
    var status: Int? = null

    @SerializedName("message")
    var message: String? = null



}
