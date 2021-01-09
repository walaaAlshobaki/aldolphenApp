package com.adolphinpos.adolphinpos.registeration.country


import com.google.gson.annotations.SerializedName

data class CountryModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("errorCode")
    var errorCode: Any,
    @SerializedName("success")
    var success: Boolean
) {
    data class Data(
        @SerializedName("alpha2code")
        var alpha2code: String,
        @SerializedName("alpha3code")
        var alpha3code: String,
        @SerializedName("callingCodes")
        var callingCodes: String,
        @SerializedName("flag")
        var flag: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String,
        @SerializedName("region")
        var region: String ,

        var type: String= "item"
    )
}