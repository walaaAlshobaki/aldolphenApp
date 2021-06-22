package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class LookupModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("newToken")
    var newToken: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?
    )
}