package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface AddHallsDelegate {

    fun didAddHallsSuccess(token: AddHallsModel)
    fun didAddHallsFail(msg: String)


}
class AddHallsPresenter (var mContext: Context) {

    var delegate: AddHallsDelegate? = null
    fun AddHallTap(name: String,branchId:Int) {




        val cred = JSONObject()

        cred.put("name",name)
        cred.put("branchId",branchId)




        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.addHalls,
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
                    delegate!!.didAddHallsSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddHallsFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddHallsFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddHallsFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didAddHallsFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddHallsFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddHallsFail(msg)
                }
            })

    }
}