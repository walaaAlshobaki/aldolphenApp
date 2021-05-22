package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import com.adolphinpos.adolphinpos.R

class HallsModel {
    var id:Int=0
    var name:Int=1
    var Image: Int = R.drawable.ic_free
    var isSelected : Boolean= false




    constructor(
        id:Int=0,
        name:Int=1,
        Image: Int = R.drawable.ic_free,
        isSelected : Boolean= false


        ){
        this.id=id
        this.name=name
        this.Image=Image
        this.isSelected=isSelected




    }
}