package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class ProductSpecificationsModel(
    @SerializedName("cost")
    var cost: Int?,
    @SerializedName("enableTrack")
    var enableTrack: Boolean?,
    @SerializedName("inStock")
    var inStock: Int?,
    @SerializedName("isPreSelcted")
    var isPreSelcted: Boolean?,
    @SerializedName("itemIds")
    var itemIds: List<String?>?,
    @SerializedName("lowStock")
    var lowStock: Int?,
    @SerializedName("optimalStock")
    var optimalStock: Int?,
    @SerializedName("priceAdjustment")
    var priceAdjustment: Int?,
    @SerializedName("productId")
    var productId: Int?,
    @SerializedName("quantity")
    var quantity: Int?
)