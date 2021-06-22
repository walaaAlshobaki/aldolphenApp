package com.adolphinpos.adolphinpos.helper

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.VariationModel
import com.google.gson.Gson
import com.manhal.lms.app.Helper.SessionManager
import com.squareup.picasso.Picasso
import java.io.File
import java.io.OutputStream

class Common {

    companion object {

        val instance = Common()

    }
    var parserJson = Gson()
    var apiDomainRoot:String=""
    var userToken:String=""
    var codeTimer:Int=0
    var branchId:Int=0
    var isLock:Boolean=false
    var userPhone:String=""
    var userPrermtion:ArrayList<Int> = arrayListOf()
    var usersDelete: UserEmployeeModel.Data? = null
    var userPayment:ArrayList<Int> = arrayListOf()
    var userCurrencyType:ArrayList<Int> = arrayListOf()
    var prermtion:ArrayList<Int> = arrayListOf()
    var ingredientsDataModel: ArrayList<VariationModel> = ArrayList()
    var variationDataModel: ArrayList<VariationModel> = ArrayList()
    val mutableList = userPrermtion.toMutableList()
    var selectedServiceId:Int=0
    var selectedServiceTypeId:Int=0
    var userEmail:String=""
    val RESULT_RELOAD_LOCATION = 122
    var session: SessionManager?=null
    val RESULT_LOAD_IMAGE_GALLERY=121
    val RESULT_LOAD_IMAGE_CAMERA=122
    var selectedCategory=122
    var selectedCategoryTax=122
    var selectedCity=0
    var selectedCountry=0


    var UPDATE_INTERVAL = 5000
    var FATEST_INTERVAL = 3000
    var DISPLACMENT = 10
    var PLAY_SERVICSE_RES_RQUEST: Int = 7001
    var MY_PERMISSSION_RQUEST_CODE: Int = 7000


    fun loadBitmapByPicasso(pContext: Context, pBitmap: Bitmap, pImageView: ImageView) {
        try {
            val uri: Uri =
                Uri.fromFile(File.createTempFile("temp_file_name", ".jpg", pContext.getCacheDir()))
            val outputStream: OutputStream? = pContext.getContentResolver().openOutputStream(uri)
            pBitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
            outputStream!!.close()

            Picasso.get().load(uri).error(R.drawable.user).placeholder(R.drawable.user).transform(
                CircleTransform()
            ).into(pImageView)
        } catch (e: java.lang.Exception) {
            Log.e("LoadBitmapByPicasso", e.message!!)
        }
    }


}