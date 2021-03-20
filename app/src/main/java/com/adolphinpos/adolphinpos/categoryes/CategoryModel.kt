package com.adolphinpos.adolphinpos.categoryes

class CategoryModel {
    var id: Int = 0
    var categoryName: String = "Guest"
    var profilePicturePath: String ?= "none"
    var isSelected : Boolean= false




    constructor(
        id: Int = 0,
        categoryName: String = "",
        profilePicturePath: String ?= "",
         isSelected : Boolean= false


    ) {
        this.id=id
        this.categoryName = categoryName
        this.profilePicturePath=profilePicturePath!!
        this.isSelected=isSelected

    }


}