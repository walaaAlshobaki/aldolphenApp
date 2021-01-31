package com.adolphinpos.adolphinpos.home

import com.adolphinpos.adolphinpos.R

class HomeModel {
    var name:String="Guest"
    var id:Int=0
    var Image: Int = R.drawable.empty
    var action:String=""
    constructor(
        name:String="",
        id:Int=0,
        Image:Int= R.drawable.empty,
    action:String="") {
        this.name = name
        this.id=id
        this.Image = Image
        this.action=action
    }
}