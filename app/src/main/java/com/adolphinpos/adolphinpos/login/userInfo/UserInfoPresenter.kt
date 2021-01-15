package com.adolphinpos.adolphinpos.login.userInfo

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import org.json.JSONObject


interface UserInfoDelegate{

    fun didGetUserInfoSuccess(response: UserInfoModel)
    fun didGetUserInfoFail(msg:String)
    fun didEmpty()


}
class UserInfoPresenter (var mContext: Context) {
    var delegate: UserInfoDelegate? = null
    fun getUserInfo(){




        val paramsDictionary = mutableMapOf<String, Any>()





//        paramsDictionary["lang"] = common.langUI
//        paramsDictionary["day"] = day
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

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
                TODO("Not yet implemented")
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
}