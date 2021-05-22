package com.adolphinpos.adolphinpos.login.userInfo

import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import org.json.JSONObject

class AvatarParser{

    var avatar:String=""

}
interface UserInfoDelegate{

    fun didGetUserInfoSuccess(response: UserInfoModel)
    fun didGetUserInfoFail(msg:String)
    fun didEmpty()
//
//    fun didAddSuccess(response: AvatarParser)
//    fun didAddFail(msg:String)
}


class UserInfoPresenter (var mContext: Context) {
    var delegate: UserInfoDelegate? = null
    fun getUserInfo(){
        val paramsDictionary = mutableMapOf<String, Any>()

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.userInfo,paramsDictionary,object :
                callBackApiGet {

            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), UserInfoModel::class.java)

                delegate!!.didGetUserInfoSuccess(responseJson)

            }

            override fun ERROR(msg: String) {
                delegate!!.didGetUserInfoFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetUserInfoFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {

            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetUserInfoFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetUserInfoFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetUserInfoFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetUserInfoFail(msg)
                delegate!!.didEmpty()
            }
        })

    }

//    fun updateInfo(Image: Bitmap, FirstName:String, LastName:String,  PhoneNumber:String, Email:String, Age:String,  CountryId:Int
//    ) {
//        val paramsDictionary = mutableMapOf<String, Any>()
//
//
//        paramsDictionary["FirstName"] = FirstName
//        paramsDictionary["LastName"] = LastName
//        paramsDictionary["Email"] = Email
//        paramsDictionary["PhoneNumber"] = PhoneNumber
//        paramsDictionary["Age"] = Age
//        paramsDictionary["CountryId"] = CountryId
//
//
//
//
//        Log.d("BBBBBBBBBBBBBBBBBB",Image.byteCount.toString())
//
//
//        serverManager.callApiUpload(this.mContext, HttpMethod.PUT, UrlAPIs.instance.updateInfo,paramsDictionary,Image,object :
//                callBackApi {
//
//
//
//            override fun SUCCESS( auth_token: String) {
//                val responseDatajson = JSONObject(auth_token.toString())
//
//                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), AvatarParser::class.java)
//
//                delegate!!.didAddSuccess(responseJson)
//            }
//
//            override fun ERROR(msg: String) {
//                delegate!!.didAddFail(msg)
//            }
//
//            override fun FAILER(msg: String) {
//                delegate!!.didAddFail(msg)
//            }
//
//            override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
//
//            }
//
//            override fun EMPTY(result: Boolean) {
//                delegate!!.didAddFail("Empty")
//            }
//
//            override fun NO_INTERNET() {
//                delegate!!.didAddFail(mContext.resources.getString(R.string.no_internet_msg))
//            }
//
//            override fun ERROR_MSG(msg: String) {
//                delegate!!.didAddFail(msg)
//            }
//
//            override fun NoMore(msg: String) {
//                delegate!!.didAddFail(msg)
//            }
//        })
//
//
//    }
}