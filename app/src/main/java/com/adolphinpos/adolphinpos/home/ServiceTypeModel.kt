package com.adolphinpos.adolphinpos.home


import com.adolphinpos.adolphinpos.R
import com.google.gson.annotations.SerializedName

data class ServiceTypeModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
            @SerializedName("id")
        var id: Int,
            @SerializedName("name")
        var name: String,
            var serviceType: Any?=0,
            var Image: Int = R.drawable.empty,
            var action:String="",
    )
}