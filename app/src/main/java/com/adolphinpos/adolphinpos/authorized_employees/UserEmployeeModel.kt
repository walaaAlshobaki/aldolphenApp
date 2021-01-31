package com.adolphinpos.adolphinpos.authorized_employees


import com.adolphinpos.adolphinpos.R
import com.google.gson.annotations.SerializedName

data class UserEmployeeModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("email")
        var email: String?,
        var Image:Int= R.drawable.ic_user
    )
}