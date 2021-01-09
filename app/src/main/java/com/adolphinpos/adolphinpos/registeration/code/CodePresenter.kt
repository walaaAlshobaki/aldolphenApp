package com.adolphinpos.adolphinpos.registeration.code


import com.google.gson.annotations.SerializedName

data class CodePresenter(
    @SerializedName("errorCode")
    var errorCode: Any?,
    @SerializedName("success")
    var success: Boolean?
)