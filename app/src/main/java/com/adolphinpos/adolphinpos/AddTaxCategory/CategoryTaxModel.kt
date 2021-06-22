package com.adolphinpos.adolphinpos.AddTaxCategory


import com.google.gson.annotations.SerializedName

data class CategoryTaxModel(
    @SerializedName("data")
    var `data`: Int,
    @SerializedName("message")
    var message: String?,
    @SerializedName("newToken")
    var newToken: Any?,
    @SerializedName("success")
    var success: Boolean?
)