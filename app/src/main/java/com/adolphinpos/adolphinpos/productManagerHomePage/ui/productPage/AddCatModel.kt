package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class AddCatModel(
    @SerializedName("data")
    var `data`: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)