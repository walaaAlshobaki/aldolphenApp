package com.adolphinpos.adolphinpos.CurrencyTypeActivity

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface CurrencyTypeDelegate{

    fun didGetCurrencyTypeSuccess(response: CurrencyTypeModel)
    fun didGetCurrencyTypeFail(msg:String)
    fun didEmpty()


}
class CurrencyTypePresenter (var mContext: Context) {
    var delegate: CurrencyTypeDelegate? = null


    fun getCurrencyType(){

        val paramsDictionary = mutableMapOf<String, Any>()

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Currency,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), CurrencyTypeModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetCurrencyTypeSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCurrencyTypeFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCurrencyTypeFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetCurrencyTypeFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCurrencyTypeFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCurrencyTypeFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCurrencyTypeFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}