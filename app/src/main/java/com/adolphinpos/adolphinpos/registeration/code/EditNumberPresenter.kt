package com.adolphinpos.adolphinpos.registeration.code

import android.content.Context
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import org.json.JSONObject


interface EditNumberDelegate {

    fun didEditNumberSuccess(token: String)
    fun didEditNumberFail(msg: String)


}
class EditNumberPresenter (var mContext: Context) {

    var delegate: EditNumberDelegate? = null

    fun editNumber(number: String) {




        val cred = JSONObject()
        cred.put("phoneNumber",number)


        serverManager.callApi(
                this.mContext,
                HttpMethod.PUT,
                UrlAPIs.instance.restPhoneNumber,
                cred,
                object : callBackApi {


                    override fun SUCCESS(jsonObject: String) {

                        delegate!!.didEditNumberSuccess(jsonObject)

                    }

                    override fun ERROR(msg: String) {
                        delegate!!.didEditNumberFail(msg)
                    }

                    override fun FAILER(msg: String) {
                        delegate!!.didEditNumberFail(msg)
                    }

                    override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                    }

                    override fun EMPTY(result: Boolean) {
                        delegate!!.didEditNumberFail("Empty")
                    }

                    override fun NO_INTERNET() {
                        delegate!!.didEditNumberFail(mContext.resources.getString(R.string.no_internet_msg))
                    }

                    override fun ERROR_MSG(msg: String) {
                        delegate!!.didEditNumberFail(msg)
                    }

                    override fun NoMore(msg: String) {
                        delegate!!.didEditNumberFail(msg)
                    }
                })

    }
}