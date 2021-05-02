package com.adolphinpos.adolphinpos.companyBranch


import com.google.gson.annotations.SerializedName

data class AddBranchModel(
    @SerializedName("data")
    var `data`: Int,
    @SerializedName("message")
    var message: String,
    @SerializedName("success")
    var success: Boolean
)