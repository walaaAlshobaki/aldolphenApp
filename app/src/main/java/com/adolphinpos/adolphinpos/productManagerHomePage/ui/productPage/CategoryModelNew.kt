package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class CategoryModelNew(
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
        @SerializedName("imagePath")
        var imagePath: String?,
        @SerializedName("name")
        var name: String?,
        var isSelected : Boolean= false
    )
}