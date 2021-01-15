package com.adolphinpos.adolphinpos.login


import com.google.gson.annotations.SerializedName

data class userModel(

    @SerializedName("AuthenticationType")
    var authenticationType: Any,
    @SerializedName("BootstrapContext")
    var bootstrapContext: Any,
    @SerializedName("Claims")
    var claims: List<Any>,
    @SerializedName("IsAuthenticated")
    var isAuthenticated: Boolean,
    @SerializedName("Label")
    var label: Any?,
    @SerializedName("Name")
    var name: String="",
    @SerializedName("NameClaimType")
    var nameClaimType: String,
    @SerializedName("RoleClaimType")
    var roleClaimType: String,
    @SerializedName("UserId")
    var userId: Int,
    @SerializedName("UserRule")
    var userRule: Int
)