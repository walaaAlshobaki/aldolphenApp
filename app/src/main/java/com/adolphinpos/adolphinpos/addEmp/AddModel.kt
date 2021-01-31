package com.adolphinpos.adolphinpos.addEmp


import com.google.gson.annotations.SerializedName

data class AddModel(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("id")
        var id: Int?
    )
}