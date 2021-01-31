package com.adolphinpos.adolphinpos.login.resetPassword


import com.google.gson.annotations.SerializedName

data class EmailVlidateModel(
    @SerializedName("data")
    var `data`: Boolean?,
    @SerializedName("success")
    var success: Boolean?
)