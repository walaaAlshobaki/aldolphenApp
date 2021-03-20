package com.adolphinpos.adolphinpos.authorized_employees


import com.google.gson.annotations.SerializedName

data class UserEmployeeModel2(
    @SerializedName("data")
    var `data`: List<Data?>?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("email")
        var email: String?,
        @SerializedName("firstName")
        var firstName: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("lastName")
        var lastName: String?,
        @SerializedName("policies")
        var policies: List<Policy?>?
    ) {
        data class Policy(
            @SerializedName("id")
            var id: Int?,
            @SerializedName("isPreDefined")
            var isPreDefined: Boolean?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("policyPermission")
            var policyPermission: List<Any?>?,
            @SerializedName("userPolicy")
            var userPolicy: List<Any?>?
        )
    }
}