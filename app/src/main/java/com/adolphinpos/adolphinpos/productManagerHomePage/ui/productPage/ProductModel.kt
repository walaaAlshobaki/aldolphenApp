package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage


import com.google.gson.annotations.SerializedName

data class ProductModel(
    @SerializedName("data")
    var `data`: Data,
    @SerializedName("message")
    var message: Any?,
    @SerializedName("success")
    var success: Boolean?
) {
    data class Data(
        @SerializedName("currentPage")
        var currentPage: Int?,
        @SerializedName("data")
        var `data`: List<Data>,
        @SerializedName("itemsPerPage")
        var itemsPerPage: Int?,
        @SerializedName("totalItems")
        var totalItems: Int?,
        @SerializedName("totalPages")
        var totalPages: Int?
    ) {
        data class Data(
            @SerializedName("barCodeImagePath")
            var barCodeImagePath: String?,
            @SerializedName("categoryId")
            var categoryId: Int?,
            @SerializedName("categoryName")
            var categoryName: String?,
            @SerializedName("cost")
            var cost: Double?,
            @SerializedName("description")
            var description: String?,
            @SerializedName("expiryDate")
            var expiryDate: Any?,
            @SerializedName("id")
            var id: Int?,
            @SerializedName("imagePath")
            var imagePath: String?,
            @SerializedName("isNew")
            var isNew: Boolean?,
            @SerializedName("isTrackable")
            var isTrackable: Boolean?,
            @SerializedName("name")
            var name: String?,
            @SerializedName("note")
            var note: String?,
            @SerializedName("oldPrice")
            var oldPrice: Double?,
            @SerializedName("price")
            var price: Double?,
            @SerializedName("qrCodeImagePath")
            var qrCodeImagePath: String?,
            @SerializedName("quantity")
            var quantity: Any?,
            @SerializedName("sku")
            var sku: String?,
            @SerializedName("taxCategoryId")
            var taxCategoryId: Int?,
            @SerializedName("taxRate")
            var taxRate: Any?,
            var isSelected : Boolean= false
        )
    }
}