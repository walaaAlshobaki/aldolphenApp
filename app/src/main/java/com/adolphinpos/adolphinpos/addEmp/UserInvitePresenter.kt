package com.adolphinpos.adolphinpos.addEmp

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.code.SendVerificationCodeDelegate
import org.json.JSONObject

interface UserInviteDelegate {

    fun didUserInviteSuccess(token: AddModel)
    fun didUserInviteFail(msg: String)


}
class UserInvitePresenter (var mContext: Context) {

    var delegate: UserInviteDelegate? = null

    fun userInvite(email:String,name:String,phoneNumber:String,policyids:ArrayList<Int>) {




        val cred = JSONObject()




        cred.put("email",email);
        cred.put("name", name);
        cred.put("phoneNumber", phoneNumber);
        cred.put("policyids", policyids)

        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.Invite,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String,auth_token:String) {


                    var responseJson =
                        common.parserJson.fromJson(jsonObject, AddModel::class.java)
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
                    delegate!!.didUserInviteSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didUserInviteFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didUserInviteFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didUserInviteFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didUserInviteFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didUserInviteFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didUserInviteFail(msg)
                }
            })

    }
}