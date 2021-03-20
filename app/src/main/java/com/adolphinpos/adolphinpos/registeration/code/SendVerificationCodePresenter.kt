package com.adolphinpos.adolphinpos.registeration.code

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordDelegate
import org.json.JSONObject


interface SendVerificationCodeDelegate {

    fun didSendVerificationCodeSuccess(token: String)
    fun didSendVerificationCodeFail(msg: String)


}
class SendVerificationCodePresenter (var mContext: Context) {

    var delegate: SendVerificationCodeDelegate? = null


    fun senCode() {




        val cred = JSONObject()



        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.code,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {


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
                    delegate!!.didSendVerificationCodeSuccess(jsonObject)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didSendVerificationCodeFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didSendVerificationCodeFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didSendVerificationCodeFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didSendVerificationCodeFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didSendVerificationCodeFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didSendVerificationCodeFail(msg)
                }
            })

    }
}