package com.adolphinpos.adolphinpos.policyManagement


import com.google.gson.annotations.SerializedName

data class AddPolicyModel(
    @SerializedName("data")
    var `data`: Int?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
)