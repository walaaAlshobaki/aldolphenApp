package com.adolphinpos.adolphinpos.CurrencyTypeActivity


import com.google.gson.annotations.SerializedName

data class CurrencyTypeModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("currenciesCode")
        var currenciesCode: String,
        @SerializedName("currenciesName")
        var currenciesName: String,
        @SerializedName("currenciesSymbol")
        var currenciesSymbol: String,
        @SerializedName("id")
        var id: Int,
        @SerializedName("name")
        var name: String
    )
}