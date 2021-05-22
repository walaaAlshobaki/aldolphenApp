package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface AddTableDelegate {

    fun didAddTableSuccess(token: AddHallsModel)
    fun didAddTableFail(msg: String)


}
class AddTablePresenter (var mContext: Context) {

    var delegate: AddTableDelegate? = null
    fun AddTableTap(id:Int,label: String,status:String,numberOfChairs:Int ,hallId:Int) {




        val cred = JSONObject()

        cred.put("label",label)
        cred.put("id",id)
        cred.put("status",status)
        cred.put("numberOfChairs",numberOfChairs)
        cred.put("hallId",hallId)




        serverManager.callApi(
            this.mContext,
            HttpMethod.PUT,
            UrlAPIs.instance.addTable,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {


                    var responseJson =
                        common.parserJson.fromJson(jsonObject.toString(), AddHallsModel::class.java)
//
//                    var email = uname
//                    val auth_token = responseJson




//
//                    userConfig = UserConfig(
//                        uname,
//                        jsonObject,
//
//                        )
//                    common.session!!.createLoginSession(userConfig)
                    delegate!!.didAddTableSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddTableFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddTableFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddTableFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didAddTableFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddTableFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddTableFail(msg)
                }
            })

    }
}