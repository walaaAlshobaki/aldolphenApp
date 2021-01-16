package com.adolphinpos.adolphinpos.home

import com.adolphinpos.adolphinpos.R

class HomeModel {
    var name:String="Guest"
    var Image: Int = R.drawable.empty
    var action:String=""
    constructor(
        name:String="",
        Image:Int= R.drawable.empty,
    action:String="") {
        this.name = name
        this.Image = Image
        this.action=action
    }
}