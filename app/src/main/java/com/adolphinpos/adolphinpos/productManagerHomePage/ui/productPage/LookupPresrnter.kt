package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject


interface LookupDelegate {


    fun didGetLookupProductSuccess(response: LookupModel)
    fun didGetLookupProductFail(msg:String)
    fun didEmptyProduct()


    fun didGetLookupControlSuccess(response: LookupModel)
    fun didGetLookupControlFail(msg:String)
    fun didEmptyControl()

}
class LookupPresrnter (var mContext: Context) {

    var delegate: LookupDelegate? = null



    fun getLookupProduct(){

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("Code","product-type")

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Lookup,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), LookupModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetLookupProductSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetLookupProductFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetLookupProductFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didEmptyProduct()


            }

            override fun NO_INTERNET() {
                delegate!!.didGetLookupProductFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetLookupProductFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetLookupProductFail(msg)
                delegate!!.didEmptyProduct()
            }
        })

    }

    fun getLookupControl(){

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("Code","control-type")

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Lookup,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), LookupModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetLookupControlSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetLookupControlFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetLookupControlFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didEmptyControl()


            }

            override fun NO_INTERNET() {
                delegate!!.didGetLookupControlFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetLookupControlFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetLookupControlFail(msg)
                delegate!!.didEmptyControl()
            }
        })

    }
}