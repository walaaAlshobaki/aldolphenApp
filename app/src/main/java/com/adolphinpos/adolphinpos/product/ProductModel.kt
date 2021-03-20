package com.adolphinpos.adolphinpos.product

class ProductModel {
    var id: Int = 0
    var productName: String = "Guest"
    var profilePicturePath: String ?= "none"
    var isSelected : Boolean= false




    constructor(
        id: Int = 0,
        productName: String = "",
        profilePicturePath: String ?= "",
        isSelected : Boolean= false


    ) {
        this.id=id
        this.productName = productName
        this.profilePicturePath=profilePicturePath!!
        this.isSelected=isSelected

    }

}