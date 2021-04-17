package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import com.adolphinpos.adolphinpos.R

class HallsInfoModel {
    var id:Int=0
    var count:Int=1
    var name:String=""
    var Image: Int = R.drawable.ic_free_info




    constructor(
        id:Int=0,
        count:Int=1,
        name:String="",
        Image: Int = R.drawable.ic_free_info,


        ){
        this.id=id
        this.name=name
        this.count=count
        this.Image=Image




    }
}