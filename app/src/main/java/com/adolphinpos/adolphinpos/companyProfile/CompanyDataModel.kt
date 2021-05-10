package com.adolphinpos.adolphinpos.companyProfile


import com.google.gson.annotations.SerializedName

data class CompanyDataModel(
    @SerializedName("data")
    var `data`: Data?,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("contactEmail")
        var contactEmail: Any?="",
        @SerializedName("contactPhoneNumber")
        var contactPhoneNumber: Any?="",
        @SerializedName("countryId")
        var countryId: Int?,
        @SerializedName("logo")
        var logo: Any?="",
        @SerializedName("name")
        var name: String?="",
        @SerializedName("taxName")
        var taxName: Any?="",
        @SerializedName("taxNumber")
        var taxNumber: Any?="",
        @SerializedName("taxRecored")
        var taxRecored: Any?="",
        @SerializedName("taxValue")
        var taxValue: Any?=""
    )
}