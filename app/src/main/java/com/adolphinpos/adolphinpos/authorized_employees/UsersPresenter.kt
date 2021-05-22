package com.adolphinpos.adolphinpos.authorized_employees

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.login.resetPassword.ResetPasswordDelegate
import org.json.JSONObject

interface UsersDelegate {

    fun didGetUsersSuccess(token: UserEmployeeModel)
    fun didGetUsersFail(msg: String)
    fun didEmpty()

    fun didDeleteUsersSuccess(token: UserEmployeeModel)
    fun didGetDeleteUsersFail(msg: String)


}
class UsersPresenter (var mContext: Context) {

    var delegate: UsersDelegate? = null

    fun getUsersTap(CId: String) {

        val paramsDictionary = mutableMapOf<String, Any>()





       paramsDictionary["CId"] = CId
//        paramsDictionary["day"] = day
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Users,paramsDictionary,object :
            callBackApiGet {




                override fun SUCCESS(jsonObject: String) {


                    var responseJson =
                        common.parserJson.fromJson(jsonObject.toString(), UserEmployeeModel::class.java)
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
                    delegate!!.didGetUsersSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didGetUsersFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didGetUsersFail(msg)
                }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {

            }



                override fun EMPTY(result: Boolean) {
                    delegate!!.didGetUsersFail("Empty")
                    delegate!!.didEmpty()
                }

                override fun NO_INTERNET() {
                    delegate!!.didGetUsersFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didGetUsersFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didGetUsersFail(msg)
                }
            })

    }

    fun getDeleteUsersTap(UId: Int) {
        val cred = JSONObject()
        cred.put("UId", UId);




//        paramsDictionary["day"] = day
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

        serverManager.callApi(
                this.mContext,
                HttpMethod.DELETE,
                UrlAPIs.instance.deleteUsers,
                cred,
                object : callBackApi {




            override fun SUCCESS(jsonObject: String) {


                var responseJson =
                        common.parserJson.fromJson(jsonObject.toString(), UserEmployeeModel::class.java)
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
                delegate!!.didGetUsersSuccess(responseJson)

            }

            override fun ERROR(msg: String) {
                delegate!!.didGetUsersFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetUsersFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

            }





            override fun EMPTY(result: Boolean) {
                delegate!!.didGetUsersFail("Empty")
                delegate!!.didEmpty()
            }

            override fun NO_INTERNET() {
                delegate!!.didGetUsersFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetUsersFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetUsersFail(msg)
            }
        })

    }

    fun getDeletePolicyTap(UId: Int) {
        val cred = JSONObject()
        cred.put("Id", UId);




//        paramsDictionary["day"] = day
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

        serverManager.callApi(
                this.mContext,
                HttpMethod.DELETE,
                UrlAPIs.instance.deletePoliicy,
                cred,
                object : callBackApi {




                    override fun SUCCESS(jsonObject: String) {


                        var responseJson =
                                common.parserJson.fromJson(jsonObject.toString(), UserEmployeeModel::class.java)
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
                        delegate!!.didGetUsersSuccess(responseJson)

                    }

                    override fun ERROR(msg: String) {
                        delegate!!.didGetUsersFail(msg)
                    }

                    override fun FAILER(msg: String) {
                        delegate!!.didGetUsersFail(msg)
                    }

                    override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                    }





                    override fun EMPTY(result: Boolean) {
                        delegate!!.didGetUsersFail("Empty")
                        delegate!!.didEmpty()
                    }

                    override fun NO_INTERNET() {
                        delegate!!.didGetUsersFail(mContext.resources.getString(R.string.no_internet_msg))
                    }

                    override fun ERROR_MSG(msg: String) {
                        delegate!!.didGetUsersFail(msg)
                    }

                    override fun NoMore(msg: String) {
                        delegate!!.didGetUsersFail(msg)
                    }
                })

    }
}