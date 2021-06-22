package com.adolphinpos.adolphinpos.AddTaxCategory

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeModel
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import org.json.JSONObject

interface CompanyTaxCategoryDelegate {

    fun didAddCompanyTaxCategorySuccess(response: Int)
    fun didAddCompanyTaxCategoryFail(msg: String)

    fun didAddCompanyTaxCategoryRateSuccess(response: CategoryTaxModel)
    fun didAddCompanyTaxCategoryRateFail(msg: String)


    fun didGetCompanyTaxCategorySuccess(response: TaxCategoryModel)
    fun didGetCompanyTaxCategoryFail(msg:String)
    fun didEmpty()


}
class CompanyTaxCategoryPresenter (var mContext: Context) {

    var delegate: CompanyTaxCategoryDelegate? = null

    fun addCompanyTaxCategory(name: String) {




        val cred = JSONObject()
        cred.put("name",name)


        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.companyTaxCategory,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {
                    Log.d("QQQQQQQQQQQQQQQQQQQQQQ", jsonObject)
//
//                    val answer = JSONObject(jsonObject)
//                    var responseJson =
//                        common.parserJson.fromJson(answer.toString(), CategoryTaxModel::class.java)
                    delegate!!.didAddCompanyTaxCategorySuccess(jsonObject.toInt())

                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddCompanyTaxCategoryFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didAddCompanyTaxCategoryFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryFail(msg)
                }
            })

    }

    fun addCompanyTaxCategoryRate(  taxCategoryId: Int,
    countryId: Int,
    cityId: Int,
    percentage: Int) {




        val cred = JSONObject()
        cred.put("taxCategoryId",taxCategoryId)
        cred.put("countryId",countryId)
        cred.put("cityId",cityId)
        cred.put("percentage",percentage)


        serverManager.callApi(
            this.mContext,
            HttpMethod.POST,
            UrlAPIs.instance.companyTaxCategoryRate,
            cred,
            object : callBackApi {


                override fun SUCCESS(jsonObject: String) {
                    var responseJson =
                        common.parserJson.fromJson(jsonObject.toString(), CategoryTaxModel::class.java)


                    delegate!!.didAddCompanyTaxCategoryRateSuccess(responseJson)

                }

                override fun ERROR(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryRateFail(msg)
                }

                override fun FAILER(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryRateFail(msg)
                }

                override fun JSON(jsonObject: JSONObject, api: ApiModel?) {

                }

                override fun EMPTY(result: Boolean) {
                    delegate!!.didAddCompanyTaxCategoryRateFail("Empty")
                }

                override fun NO_INTERNET() {
                    delegate!!.didAddCompanyTaxCategoryRateFail(mContext.resources.getString(R.string.no_internet_msg))
                }

                override fun ERROR_MSG(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryRateFail(msg)
                }

                override fun NoMore(msg: String) {
                    delegate!!.didAddCompanyTaxCategoryRateFail(msg)
                }
            })

    }



    fun getCompanyTaxCategory(){

        val paramsDictionary = mutableMapOf<String, Any>()

        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.companyTax,paramsDictionary,object :
            callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())
                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), TaxCategoryModel::class.java)
                Log.d("EEEEEEEEEEEEEEEEE",responseDatajson.toString())

                delegate!!.didGetCompanyTaxCategorySuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCompanyTaxCategoryFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCompanyTaxCategoryFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didEmpty()


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCompanyTaxCategoryFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCompanyTaxCategoryFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCompanyTaxCategoryFail(msg)
                delegate!!.didEmpty()
            }
        })

    }
}