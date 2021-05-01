package com.adolphinpos.adolphinpos.paymentMethods

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.AddCurrencyTypeModel
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONArray
import org.json.JSONObject

interface PaymentMethoodDelegate{

    fun didGetPaymentMethoodSuccess(response: PaymentMethoodModel)
    fun didGetPaymentMethoodFail(msg:String)
    fun didEmpty()

    fun didAddPaymentMethoodSuccess(response: AddCurrencyTypeModel)
    fun didAddPaymentMethoodFail(msg:String)

}
class PaymentMethoodPresnter (var mContext: Context) {
    var delegate: PaymentMethoodDelegate? = null


    fun getPaymentMethood(){

        val paramsDictionary = mutableMapOf<String, Any>()

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.PaymentMethood,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), PaymentMethoodModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetPaymentMethoodSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetPaymentMethoodFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetPaymentMethoodFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetPaymentMethoodFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetPaymentMethoodFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetPaymentMethoodFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetPaymentMethoodFail(msg)
                delegate!!.didEmpty()
            }
        })

    }

    fun addPaymentMethood(  CurrencyTypeIds: ArrayList<Int>){


        val cred = JSONObject()





        val arr = JSONArray()
        for (item in CurrencyTypeIds) {

            arr.put(item)

        }
        cred.put("paymentMethodIds", arr)
        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.addPaymentMethood,
            cred,
            object : callBackApi {



                override fun SUCCESS(jsonObject: String) {

                    val responseDatajson = JSONObject(jsonObject.toString())

                    val responseJson = common.parserJson.fromJson(responseDatajson.toString(), AddCurrencyTypeModel::class.java)

                    delegate!!.didAddPaymentMethoodSuccess(responseJson)





                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddPaymentMethoodFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddPaymentMethoodFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {
                    TODO("Not yet implemented")
                }


                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddPaymentMethoodFail("Empty")


                }

                override fun NO_INTERNET() {
                    delegate!!.didAddPaymentMethoodFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddPaymentMethoodFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddPaymentMethoodFail(msg)

                }
            })

    }

}