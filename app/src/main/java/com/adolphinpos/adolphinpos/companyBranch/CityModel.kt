package com.adolphinpos.adolphinpos.companyBranch


import com.google.gson.annotations.SerializedName

data class CityModel(
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
        @SerializedName("name")
        var name: String?
    )
}