package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan


import com.google.gson.annotations.SerializedName

data class TableModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("id")
        var id: Int?,
        @SerializedName("label")
        var label: String?,
        @SerializedName("numberOfChairs")
        var numberOfChairs: Int?,
        @SerializedName("status")
        var status: Int?
    )
}