package com.adolphinpos.adolphinpos.employee_permissions

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.addEmp.PoliicyDelegate
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import org.json.JSONObject

interface PoliicyPermissionDelegate{

    fun didGetPoliicyPermissionSuccess(response: PoliicyPermissionModel)
    fun didGetPoliicyPermissionFail(msg:String)
    fun didEmptyPoliicyPermission()


}
class PoliicyPermissionPresenter (var mContext: Context) {
    var delegate: PoliicyPermissionDelegate? = null
    fun getPoliicyPermission(id:String){

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary["id"] = id

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.PoliicyPermissions,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), PoliicyPermissionModel::class.java)

                delegate!!.didGetPoliicyPermissionSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetPoliicyPermissionFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetPoliicyPermissionFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
                delegate!!.didEmptyPoliicyPermission()
            }
        })

    }

    fun getPermission(){

        val paramsDictionary = mutableMapOf<String, Any>()


        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Permissions,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), PoliicyPermissionModel::class.java)

                delegate!!.didGetPoliicyPermissionSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetPoliicyPermissionFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetPoliicyPermissionFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetPoliicyPermissionFail(msg)
                delegate!!.didEmptyPoliicyPermission()
            }
        })

    }


}