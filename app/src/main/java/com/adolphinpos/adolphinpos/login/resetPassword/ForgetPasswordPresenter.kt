package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.login.LoginDelegate
import org.json.JSONObject


interface ForgetPasswordDelegate {

    fun didSendSuccess(token: String)
    fun didSendFail(msg: String)


}

class ForgetPasswordPresenter (var mContext: Context) {

    var delegate: ForgetPasswordDelegate? = null
    fun sendEmailTap(uname: String) {




        val cred = JSONObject()

        cred.put("email",uname)


        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.email,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String,auth_token:String) {


//                    var responseJson =
//                        common.parserJson.fromJson(jsonObject.toString(), String()::class.java)
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
                    delegate!!.didSendSuccess(jsonObject)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didSendFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didSendFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didSendFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didSendFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didSendFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didSendFail(msg)
                }
            })

    }
}