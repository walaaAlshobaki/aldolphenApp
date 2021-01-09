package com.adolphinpos.adolphinpos.login


import com.google.gson.annotations.SerializedName

data class userModel(
    @SerializedName("Actor")
    var actor: Any,
    @SerializedName("AuthenticationType")
    var authenticationType: Any,
    @SerializedName("BootstrapContext")
    var bootstrapContext: Any,
    @SerializedName("Claims")
    var claims: List<Any>,
    @SerializedName("Email")
    var email: String,
    @SerializedName("FirstName")
    var firstName: String,
    @SerializedName("IsAuthenticated")
    var isAuthenticated: Boolean,
    @SerializedName("Label")
    var label: Any?,
    @SerializedName("LastName")
    var lastName: String,
    @SerializedName("Name")
    var name: String="",
    @SerializedName("NameClaimType")
    var nameClaimType: String,
    @SerializedName("PhoneNumber")
    var phoneNumber: String,
    @SerializedName("RoleClaimType")
    var roleClaimType: String,
    @SerializedName("UserId")
    var userId: Int,
    @SerializedName("UserRule")
    var userRule: Int
)