package com.adolphinpos.adolphinpos.addCategory

import android.content.Context
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.ServerManager.*
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.CategoryModelNew
import org.json.JSONObject

interface CategoryDelegate{

    fun didGetCategorySuccess(response: CategoryModelNew)
    fun didGetCategoryFail(msg:String)
    fun didEmpty()


}
class CategoryPresenter  (var mContext: Context) {

    var delegate: CategoryDelegate? = null


    fun getCategories(){
        val paramsDictionary = mutableMapOf<String, Any>()



        serverManagerGet.callApi(this.mContext, HttpMethod.GET, UrlAPIs.instance.Category,paramsDictionary,object :
                callBackApiGet {

            override fun SUCCESS(jsonObject: String) {

                val responseDatajson = JSONObject(jsonObject.toString())

                val responseJson = common.parserJson.fromJson(responseDatajson.toString(), CategoryModelNew::class.java)


                delegate!!.didGetCategorySuccess(responseJson)

            }

            override fun ERROR(msg: String) {
                delegate!!.didGetCategoryFail(msg)
            }

            override fun FAILER(msg: String) {
                delegate!!.didGetCategoryFail(msg)
            }

            override fun JSON(jsonObject: JSONObject, api: ApiModelGet?) {

            }



            override fun EMPTY(result: Boolean) {
                delegate!!.didGetCategoryFail("Empty")


            }

            override fun NO_INTERNET() {
                delegate!!.didGetCategoryFail(mContext.resources.getString(R.string.no_internet_msg))
            }

            override fun ERROR_MSG(msg: String) {
                delegate!!.didGetCategoryFail(msg)
            }

            override fun NoMore(msg: String) {
                delegate!!.didGetCategoryFail(msg)
                delegate!!.didEmpty()
            }
        })

    }


}