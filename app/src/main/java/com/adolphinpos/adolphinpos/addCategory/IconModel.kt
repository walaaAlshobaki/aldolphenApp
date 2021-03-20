package com.adolphinpos.adolphinpos.addCategory

class IconModel {
    var id: Int = 0
    var profilePicturePath: String ?= "none"
    var isSelected : Boolean= false




    constructor(
        id: Int = 0,
        profilePicturePath: String ?= "",
        isSelected : Boolean= false


    ) {
        this.id=id
        this.profilePicturePath=profilePicturePath!!
        this.isSelected=isSelected

    }

}