package com.adolphinpos.adolphinpos.companyProfile

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import org.json.JSONObject

interface CompanyProfileDelegate{

    fun didGetCompanyProfileSuccess(response: UserInfoModel)
    fun didGetCompanyProfileFail(msg:String)
    fun didEmpty()


}
class CompanyProfilePresenter  (var mContext: Context) {

    var delegate: CompanyProfileDelegate? = null
    fun getUserInfo(){
        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary["CId"] = userInfo.companyId
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.CompanyInfo,paramsDictionary,object :
                callBackApiGet {
            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), UserInfoModel::class.java)

                delegate!!.didGetCompanyProfileSuccess(responseJson)
            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCompanyProfileFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCompanyProfileFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {

            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetCompanyProfileFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCompanyProfileFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCompanyProfileFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCompanyProfileFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}