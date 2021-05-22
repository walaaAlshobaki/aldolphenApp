package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan


import com.google.gson.annotations.SerializedName

data class AddHallsModel(
    @SerializedName("data")
    var `data`: Int?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
)