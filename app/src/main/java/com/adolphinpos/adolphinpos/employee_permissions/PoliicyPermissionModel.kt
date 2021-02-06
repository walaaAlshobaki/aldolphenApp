package com.adolphinpos.adolphinpos.employee_permissions


import com.google.gson.annotations.SerializedName

data class PoliicyPermissionModel(
    @SerializedName("data")
    var `data`: List<Data>,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("description")
        var description: String?,
        @SerializedName("id")
        var id: Int?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("policyPermission")
        var policyPermission: List<Any?>?,
        var isSelect:Boolean=false
    )
}