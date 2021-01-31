package com.adolphinpos.adolphinpos.addEmp

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import org.json.JSONObject

interface PoliicyDelegate{

    fun didGetPoliicySuccess(response: PoliicyModel)
    fun didGetPoliicyFail(msg:String)
    fun didEmpty()


}
class PoliicyPresenter (var mContext: Context) {
    var delegate: PoliicyDelegate? = null

    fun getPoliicy(){

        val paramsDictionary = mutableMapOf<String, Any>()
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Poliicy,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), PoliicyModel::class.java)

                delegate!!.didGetPoliicySuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetPoliicyFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetPoliicyFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetPoliicyFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetPoliicyFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetPoliicyFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetPoliicyFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}