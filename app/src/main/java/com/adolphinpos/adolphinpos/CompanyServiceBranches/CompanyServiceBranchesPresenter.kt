package com.adolphinpos.adolphinpos.CompanyServiceBranches

import android.content.Context
import android.graphics.Bitmap
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.home.ServicesDelegate
import com.adolphinpos.adolphinpos.home.ServiesModel
import org.json.JSONObject

class AvatarParser{

    var avatar:String=""

}

interface CompanyServiceBranchesDelegate{

    fun didAddSuccess(response: AvatarParser)
    fun didAddFail(msg:String)



}
//CompanyServiceBranchesModel
class CompanyServiceBranchesPresenter(var mContext: Context) {

    var delegate: CompanyServiceBranchesDelegate? = null

    fun uploadImageTap(Logo: Bitmap,ComapnyId:Int,ServieId:Int,Servicetype:Int,PhoneNumber:String,Email:String,TaxNumber:String,TaxRecored:String,
                       Name:String,OwnerId:Int
    ) {
        val paramsDictionary = mutableMapOf<String, Any>()


        paramsDictionary["ComapnyId"] = ComapnyId
        paramsDictionary["ServieId"] = ServieId
        paramsDictionary["Servicetype"] = Servicetype
        paramsDictionary["PhoneNumber"] = PhoneNumber
        paramsDictionary["Email"] = Email
        paramsDictionary["TaxNumber"] = TaxNumber
        paramsDictionary["TaxRecored"] = TaxRecored
        paramsDictionary["Name"] = Name
        paramsDictionary["OwnerId"] = OwnerId




        serverManager.callApiUpload(this.mContext, HttpMethod.POST, UrlAPIs.instance.newBranch,paramsDictionary,Logo,object :
            callBackApi {



            override fun SUCCESS( auth_token: String) {
                val responseDatajson = JSONObject(auth_token.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), AvatarParser::class.java)

                delegate!!.didAddSuccess(responseJson)
            }

            override fun ERROR(msg: String) {
                delegate!!.didAddFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didAddFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

            }

            override fun EMPTY(result: Boolean) {
                delegate!!.didAddFail("Empty")
            }

            override fun NO_INTERNET() {
                delegate!!.didAddFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didAddFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didAddFail(msg)
            }
        })


    }
}