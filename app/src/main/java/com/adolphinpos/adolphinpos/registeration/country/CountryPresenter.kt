package com.adolphinpos.adolphinpos.registeration.country

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface CountryDelegate{

    fun didGetCountrySuccess(response: CountryModel)
    fun didGetCountryFail(msg:String)
    fun didEmpty()


}
class CountryPresenter(var mContext: Context) {
    var delegate: CountryDelegate? = null
    fun getCountry(){




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

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Country,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), CountryModel::class.java)

                delegate!!.didGetCountrySuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCountryFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCountryFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetCountryFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCountryFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCountryFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCountryFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}