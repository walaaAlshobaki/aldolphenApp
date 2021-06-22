package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class AttributesModel(
    @SerializedName("attributeName")
    var attributeName: String?,
    @SerializedName("productAttributes")
    var productAttributes: ProductAttributes?
) {
    data class ProductAttributes(
        @SerializedName("attributeValues")
        var attributeValues: List<String?>?,
        @SerializedName("controlType")
        var controlType: Int?,
        @SerializedName("isRequired")
        var isRequired: Boolean?,
        @SerializedName("isShowable")
        var isShowable: Boolean?,
        @SerializedName("name")
        var name: String?,
        @SerializedName("numberOfChoices")
        var numberOfChoices: Int?,
        @SerializedName("productId")
        var productId: Int?
    )
}