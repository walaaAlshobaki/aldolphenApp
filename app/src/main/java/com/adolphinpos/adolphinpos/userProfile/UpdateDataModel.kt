package com.adolphinpos.adolphinpos.userProfile


import com.google.gson.annotations.SerializedName

data class UpdateDataModel(
    @SerializedName("data")
    var `data`: Any?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)