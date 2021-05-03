package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface HallsDelegate{

    fun didGetHallsySuccess(response: MainHallsModel)
    fun didGetHallsFail(msg:String)
    fun didEmpty()



    fun didGetTablesSuccess(response: TableModel)
    fun didGetTablesFail(msg:String)
    fun didEmptyTables()
}
class HallsPresenter (var mContext: Context) {
    var delegate: HallsDelegate? = null

    fun getHalls(){

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("BranchId", common.branchId)
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Hall,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), MainHallsModel::class.java)

                delegate!!.didGetHallsySuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetHallsFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetHallsFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetHallsFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetHallsFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetHallsFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetHallsFail(msg)
                delegate!!.didEmpty()
            }
        })

    }

    fun getTables(HallId:Int){

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("HallId", HallId)
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Tables,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), TableModel::class.java)

                delegate!!.didGetTablesSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetTablesFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetTablesFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetTablesFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetTablesFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetTablesFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetTablesFail(msg)
                delegate!!.didEmptyTables()
            }
        })

    }
}