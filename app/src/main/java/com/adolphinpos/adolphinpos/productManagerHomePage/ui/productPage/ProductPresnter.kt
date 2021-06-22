package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import org.json.JSONObject

interface ProductDelegate {

    fun didGetProductSuccess(response: ProductModel.Data)
    fun didGetProductFail(msg: String)
    fun didProductEmpty()
}
class ProductPresnter (var mContext: Context) {
    var delegate: ProductDelegate? = null

    fun getProduct(CompanyId:Int,PageNumber:Int,PageSize:Int,categoryId:Int=0,name:String=""){

        Log.d("RRRRRRRRRRRRRRRRRRRRRRRRRRR","####################################################")

        val paramsDictionary = mutableMapOf<String, Any>()
        paramsDictionary.put("CompanyId",CompanyId)
        paramsDictionary.put("PageNumber",PageNumber)
        paramsDictionary.put("PageSize",PageSize)
        if (categoryId!=0){
            paramsDictionary.put("categoryId",categoryId)

        }
        if (name!=""){
            paramsDictionary.put("name",name)

        }
        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.searchProduct,paramsDictionary,object :
                callBackApiGet {



            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), ProductModel.Data::class.java)

                delegate!!.didGetProductSuccess(responseJson)





            }

            override fun ERROR(msg: String) {
                delegate!!.didGetProductFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetProductFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {
                TODO("Not yet implemented")
            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetProductFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetProductFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetProductFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetProductFail(msg)
                delegate!!.didProductEmpty()
            }
        })

    }
}