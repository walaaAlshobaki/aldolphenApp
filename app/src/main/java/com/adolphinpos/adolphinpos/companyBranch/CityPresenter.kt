package com.adolphinpos.adolphinpos.companyBranch

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface CityDelegate{

    fun didGetCitySuccess(response: CityModel)
    fun didGetCityFail(msg:String)
    fun didEmpty()

}
class CityPresenter (var mContext: Context) {
    var delegate: CityDelegate? = null

    fun getCity(id: Int) {

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("CountryId",id)
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.City,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), CityModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetCitySuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCityFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCityFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetCityFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCityFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCityFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCityFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}