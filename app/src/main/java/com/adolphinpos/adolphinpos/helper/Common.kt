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
    var branchId:Int=27
    var isLock:Boolean=false
    var userPhone:String=""
    var userPrermtion:ArrayList<Int> = arrayListOf()
    var userPayment:ArrayList<Int> = arrayListOf()
    var userCurrencyType:ArrayList<Int> = arrayListOf()
    var prermtion:ArrayList<Int> = arrayListOf()
    val mutableList = userPrermtion.toMutableList()
    var selectedServiceId:Int=0
    var selectedServiceTypeId:Int=0
    var userEmail:String=""
    val RESULT_RELOAD_LOCATION = 122
    var session: SessionManager?=null
    val RESULT_LOAD_IMAGE_GALLERY=121
    val RESULT_LOAD_IMAGE_CAMERA=122

    var UPDATE_INTERVAL = 5000
    var FATEST_INTERVAL = 3000
    var DISPLACMENT = 10
    var PLAY_SERVICSE_RES_RQUEST: Int = 7001
    var MY_PERMISSSION_RQUEST_CODE: Int = 7000


}