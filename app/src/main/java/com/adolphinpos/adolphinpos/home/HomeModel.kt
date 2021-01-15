package com.adolphinpos.adolphinpos.home

import com.adolphinpos.adolphinpos.R

class HomeModel {
    var name:String="Guest"
    var Image: Int = R.drawable.empty
    constructor(
        name:String="",
        Image:Int= R.drawable.empty) {
        this.name = name
        this.Image = Image
    }
}