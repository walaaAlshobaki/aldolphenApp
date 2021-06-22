package com.adolphinpos.adolphinpos.login

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.home.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.SplashActivity
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.login.userInfo.TestModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoPresenter
import kotlinx.android.synthetic.main.activity_add_employee.*
import kotlinx.android.synthetic.main.activity_loading_screen.*
import java.lang.Exception


class LoadingScreenActivity : AppCompatActivity() , UserInfoDelegate {
    var mPresenter: UserInfoPresenter? = null
    private var i = 0

    var bundle:Bundle?=null
    private val hdlr = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        mPresenter = UserInfoPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUserInfo()
         bundle = intent.extras
        i = pBar3.getProgress()

        Thread {
            while (i < 100) {
                i += 1
                // Update the progress bar and display the current value in text view
                hdlr.post(Runnable {
                    pBar3.setProgress(i)
//                    txtView.setText(i.toString() + "/" + pgsBar.getMax())
                })
                try {
                    // Sleep for 100 milliseconds to show the progress slowly.
                    Thread.sleep(100)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

        }.start()

//        Handler().postDelayed({ /* Create an Intent that will start the Menu-Activity. */
//            val mainIntent = Intent(applicationContext, MainActivity::class.java)
//            startActivity(mainIntent)
//            finish()
//        }, 1500)

    }

    override fun didGetUserInfoSuccess(response: UserInfoModel) {
      var profilePicturePathString=""
        var ageString=0
        var branchIdString=0
        Log.d("SSSSSSSSSSSSSSSSSSS",response.contryId.toString())
        try {
            if (response.profilePicturePath==null){
                Log.d("SSSSSSSSSSSSSSSS",response.profilePicturePath.toString())
                profilePicturePathString=""
            }else{

                profilePicturePathString=response.profilePicturePath.toString()
            }
            if (response.age==null){
                Log.d("SSSSSSSSSSSSSSS",response.age.toString())
                ageString=0
            }else{

                ageString=response.age as Int
            }

            if (response.branchId==null){
                Log.d("SSSSSSSSSSSSSSSSSSS",response.branchId.toString())
                branchIdString=0
            }else{

                branchIdString=response.branchId as Int
            }
            userInfo = UserInfoModel(
                response.firstName!!,
                response.lastName!!,
                response.isVerfied!!,
                response.phoneNumber!!,
                response.email!!,
                userConfig.auth_token,
                userConfig.userid.toInt(),
               response.companyId!!.toString(),
                    profilePicturePathString,ageString,branchIdString,response.contryId!!


//
//,response.age!!,response.branchId

            )
            common.session!!.createLoginSession(userInfo)
            Log.d("createLoginSession2",common.session!!.createLoginSession(userInfo).toString())
            userInfo= UserInfoModel()
//        common.session!!.createLoginSession(userConfig)

            if (bundle!=null){
                Log.d("createLoginSession2", bundle!!.getString("action").toString())

                if (!bundle!!.getString("action").isNullOrEmpty() && bundle!!.getString("action")=="login"){
                    val mainIntent = Intent(applicationContext, SplashActivity::class.java)

                    intent.putExtra("action","login")
                    startActivity(mainIntent)
                    finish()
                }else if(!bundle!!.getString("action").isNullOrEmpty() && bundle!!.getString("action")=="resgister"){
                    val mainIntent = Intent(applicationContext, SplashActivity::class.java)
                    intent.putExtra("action","resgister")
                    startActivity(mainIntent)
                    finish()
                }
            }

        }catch (e:Exception){
            println(e.stackTrace)
            Log.d("EEEEEEEEEEEE","***********************************************")
        }

    }

    override fun didGetUserInfoFail(msg: String) {
        Log.d("didGetUserInfoFail",msg)
    }

    override fun didEmpty() {

    }




}