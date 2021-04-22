package com.adolphinpos.adolphinpos.helper

import com.google.gson.Gson
import com.manhal.lms.app.Helper.SessionManager

class Common {

    companion object {

        val instance = Common()

    }
    var parserJson = Gson()
    var apiDomainRoot:String=""
    var userToken:String=""
    var codeTimer:Int=0
    var userPhone:String=""
    var userPrermtion:ArrayList<Int> = arrayListOf(1)
    var prermtion:ArrayList<Int> = arrayListOf(0)
    val mutableList = userPrermtion.toMutableList()
    var selectedServiceId:Int=0
    var selectedServiceTypeId:Int=0
    var userEmail:String=""
    var session: SessionManager?=null
    val RESULT_LOAD_IMAGE_GALLERY=121
    val RESULT_LOAD_IMAGE_CAMERA=122


}