package com.adolphinpos.adolphinpos.paymentMethods


import com.google.gson.annotations.SerializedName

data class PaymentMethoodModel(
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
        var name: String?,
        var isSelected : Boolean= false
    )
}