package com.adolphinpos.adolphinpos.home

import android.graphics.drawable.Drawable
import com.adolphinpos.adolphinpos.R
import java.io.Serializable


class ChildModel(id: Int = -2, fullname: String="", avatar: Int = R.drawable.ic_user, type:String="item"):Serializable {

    var type:String
    var id:Int
    var fullname:String

    var avatar:Int
    init {


        this.type=type
        this.id = id
        this.fullname = fullname

        this.avatar = avatar


    }
}