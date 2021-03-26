package com.adolphinpos.adolphinpos.home

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import org.json.JSONObject

interface ServicesDelegate{

    fun didGetServicesSuccess(response: ServiceTypeModel)
    fun didGetServicesFail(msg:String)
    fun didEmpty()


}
class ServicesPresenter (var mContext: Context) {
    var delegate: ServicesDelegate? = null


    fun getService(){




        val paramsDictionary = mutableMapOf<String, Any>()





        paramsDictionary["SCId"] = 2
//        paramsDictionary["day"] = day
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.ServiceTypes,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), ServiceTypeModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetServicesSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetServicesFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetServicesFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetServicesFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetServicesFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetServicesFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetServicesFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}