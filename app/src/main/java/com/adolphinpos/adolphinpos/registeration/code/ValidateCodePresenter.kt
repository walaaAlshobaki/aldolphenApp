package com.adolphinpos.adolphinpos.registeration.code

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import org.json.JSONObject



interface ValidateCodeDelegate{

    fun didGetValidateCodeSuccess(response: String)
    fun didGetValidateCodeFail(msg:String)
    fun didEmpty()


}
class ValidateCodePresenter(var mContext: Context) {
    var delegate: ValidateCodeDelegate? = null



    fun getCode(code:String) {


        val paramsDictionary = mutableMapOf<String, Any>()


//        paramsDictionary["lang"] = common.langUI
        paramsDictionary["Code"] = code
//
//
//        if(common.selectedChild != 0){
//
//            paramsDictionary["childid"] = "${common.selectedChild}"
//
//        }

        serverManagerGet.callApi(
            this.mContext,
            HttpMethod.GET,
            UrlAPIs.instance.Validate,
            paramsDictionary,
            object :
                callBackApiGet {


                override fun SUCCESS(jsonObject: String) {

                    val responseDatajson = JSONObject(jsonObject.toString())

                    val responseJson = common.parserJson.fromJson(
                        responseDatajson.toString(),
                        String::class.java
                    )

                    delegate!!.didGetValidateCodeSuccess(responseJson)


                }

                override fun ERROR(msg: String) {
                    delegate!!.didGetValidateCodeFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didGetValidateCodeFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                    TODO("Not yet implemented")
                }


                override fun EMPTY(result: Boolean) {
                    delegate!!.didGetValidateCodeFail("Empty")


                }

                override fun NO_INTERNET() {
                    delegate!!.didGetValidateCodeFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didGetValidateCodeFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didGetValidateCodeFail(msg)
                    delegate!!.didEmpty()
                }
            })

    }

}
