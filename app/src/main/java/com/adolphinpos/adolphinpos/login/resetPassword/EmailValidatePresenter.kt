package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.code.SendVerificationCodeDelegate
import org.json.JSONObject

interface EmailValidateDelegate {

    fun didEmailValidateSuccess(token: EmailVlidateModel)
    fun didEmailValidateFail(msg: String)


}
class EmailValidatePresenter (var mContext: Context) {
    var delegate: EmailValidateDelegate? = null
    fun emailValidate(email:String ,code :String) {



        val cred = JSONObject()

        cred.put("code",code);
        cred.put("email", email);



        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.emailValidate,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {


                    var responseJson =
                        common.parserJson.fromJson(jsonObject.toString(), EmailVlidateModel::class.java)
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
                    delegate!!.didEmailValidateSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didEmailValidateFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didEmailValidateFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didEmailValidateFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didEmailValidateFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didEmailValidateFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didEmailValidateFail(msg)
                }
            })

    }
}
