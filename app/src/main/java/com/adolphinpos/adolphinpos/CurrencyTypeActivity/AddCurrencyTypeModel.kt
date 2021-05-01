package com.adolphinpos.adolphinpos.CurrencyTypeActivity


import com.google.gson.annotations.SerializedName

data class AddCurrencyTypeModel(
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)