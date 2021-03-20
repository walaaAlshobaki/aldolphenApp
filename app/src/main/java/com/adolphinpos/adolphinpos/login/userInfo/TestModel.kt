package com.adolphinpos.adolphinpos.login.userInfo


import com.google.gson.annotations.SerializedName

data class TestModel(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: String?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("age")
        var age: Any?,
        @SerializedName("branchId")
        var branchId: Any?,
        @SerializedName("companyId")
        var companyId: Int?,
        @SerializedName("email")
        var email: String?,
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("isVerfied")
        var isVerfied: Boolean?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("permissionsActions")
        var permissionsActions: List<Int?>?,
        @SerializedName("phoneNumber")
        var phoneNumber: String?,
        @SerializedName("profilePicturePath")
        var profilePicturePath: Any?
    )
}