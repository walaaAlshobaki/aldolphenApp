package com.adolphinpos.adolphinpos.login

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.registeration.register.RegisterModel
import org.json.JSONObject


interface LoginDelegate {

    fun didLoginSuccessful(token: String)
    fun didLoginFail(msg: String)


}

class LoginPresenter(var mContext: Context) {

    var delegate: LoginDelegate? = null
    fun loginTap(uname: String, pws: String) {


        val paramsDictionary = mutableMapOf<String, Any>()




        paramsDictionary["userEmail"] = uname
        paramsDictionary["userPassword"] = pws
        val cred = JSONObject()

        cred.put("userEmail",uname);
        cred.put("userPassword", pws);


        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.login,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {
                    Log.d("WWWWWWWWWWWWWWWWWWWWW", jsonObject)
                    val responseDatajson = JSONObject(jsonObject.toString())

                    val responseJson = common.parserJson.fromJson(responseDatajson.toString(), String::class.java)


//                    userConfig = UserConfig(
//                        uname,
//                        jsonObject,
//
//                        )
//                    common.session!!.createLoginSession(userConfig)
                    delegate!!.didLoginSuccessful(jsonObject)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didLoginFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didLoginFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didLoginFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didLoginFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didLoginFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didLoginFail(msg)
                }
            })

    }
}