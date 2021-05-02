package com.adolphinpos.adolphinpos.companyBranch

import android.content.Context
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.AddCurrencyTypeModel
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodDelegate
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodModel
import org.json.JSONArray
import org.json.JSONObject

interface BranchDelegate{


    fun didAddBranchSuccess(response: AddBranchModel)
    fun didAddBranchFail(msg:String)

}
class BranchPresnter (var mContext: Context) {
    var delegate: BranchDelegate? = null
    fun addPaymentMethood(   name: String,
    zipCode: Int,
    streetNumber:String,
    street: String,
    longitude: String,
    latitude: String,
    gpsUrl: String,
    floorNumber: String,
    country:String,
    buildingNumber: String,
    apparentNumber:String,
    address1: String,
    address2: String,
    cityId: Int,
    countryId: Int,
    phoneNumber: String){


        val cred = JSONObject()





        cred.put("name", name)
        cred.put("zipCode", zipCode)
        cred.put("streetNumber", streetNumber)
        cred.put("street", street)
        cred.put("longitude", longitude)
        cred.put("latitude", latitude)
        cred.put("gpsUrl", gpsUrl)
        cred.put("floorNumber", floorNumber)
        cred.put("country", country)
        cred.put("buildingNumber", buildingNumber)
        cred.put("apparentNumber", apparentNumber)
        cred.put("address1", address1)
        cred.put("address2", address2)
        cred.put("cityId", cityId)
        cred.put("countryId", countryId)
        cred.put("phoneNumber", phoneNumber)
        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.newCompanyBranch,
            cred,
            object : callBackApi {



                override fun SUCCESS(jsonObject: String) {

                    val responseDatajson = JSONObject(jsonObject.toString())

                    val responseJson = common.parserJson.fromJson(responseDatajson.toString(), AddBranchModel::class.java)

                    delegate!!.didAddBranchSuccess(responseJson)





                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddBranchFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddBranchFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
                    TODO("Not yet implemented")
                }


                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddBranchFail("Empty")


                }

                override fun NO_INTERNET() {
                    delegate!!.didAddBranchFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddBranchFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddBranchFail(msg)

                }
            })

    }
}