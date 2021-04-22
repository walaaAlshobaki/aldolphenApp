package com.adolphinpos.adolphinpos.registeration.register

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.userModel
import org.json.JSONObject

interface RegisterationDelegate {

    fun didRegisterationSuccess(token: String)
    fun didRegisterationFail(msg: String)


}
class RegisterationPresenter(var mContext: Context) {
    var delegate: RegisterationDelegate? = null
    fun RegisterTap(companyName: String, firstName: String,lastName:String,countryId:Int,email:String,phoneNumber:String,password:String) {
        val cred = JSONObject()

        cred.put("companyName",companyName);
        cred.put("firstName", firstName);
        cred.put("lastName", lastName);
        cred.put("countryId", countryId);
        cred.put("email", email);
        cred.put("phoneNumber", phoneNumber);
        cred.put("password", password);


        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.Company,
            cred,
            object : callBackApi {


                override fun SUCCESS(auth_token:String) {

//                        val responseDatajson = JSONObject(jsonObject.toString())

//                        val responseJson = common.parserJson.fromJson(responseDatajson.toString(), userModel::class.java)


//                    userConfig = UserConfig(
//                        uname,
//                        jsonObject,
//
//                        )
//                    common.session!!.createLoginSession(userConfig)
                    delegate!!.didRegisterationSuccess(auth_token)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didRegisterationFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didRegisterationFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didRegisterationFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didRegisterationFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didRegisterationFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didRegisterationFail(msg)
                }
            })

    }
}