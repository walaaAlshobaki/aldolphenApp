package com.adolphinpos.adolphinpos.createPOS

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.addEmp.PoliicyDelegate
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import org.json.JSONObject

interface ServiceTypeDelegate{

    fun didGetServiceTypeSuccess(response: PoliicyModel)
    fun didGetServiceTypeFail(msg:String)
    fun didEmpty()
    }
class ServiceTypePresenter (var mContext: Context){
    var delegate: ServiceTypeDelegate? = null

    fun getPoliicy(){

        val paramsDictionary = mutableMapOf<String, Any>()
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.ServiceType,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), PoliicyModel::class.java)

                delegate!!.didGetServiceTypeSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetServiceTypeFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetServiceTypeFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetServiceTypeFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetServiceTypeFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetServiceTypeFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetServiceTypeFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}