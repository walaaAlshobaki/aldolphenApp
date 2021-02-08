package com.adolphinpos.adolphinpos.registeration.register


import com.google.gson.annotations.SerializedName

data class RegisterModel(
    @SerializedName("data")
    var `data`: String?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)