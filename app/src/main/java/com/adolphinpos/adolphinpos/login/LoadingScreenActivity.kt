package com.adolphinpos.adolphinpos.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.CompanyServiceBranches.AvatarParser
import com.adolphinpos.adolphinpos.home.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.login.userInfo.TestModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoPresenter
import kotlinx.android.synthetic.main.activity_loading_screen.*
import java.lang.Exception


class LoadingScreenActivity : AppCompatActivity() , UserInfoDelegate {
    var mPresenter: UserInfoPresenter? = null
    private var i = 0
    private val hdlr = Handler()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        mPresenter = UserInfoPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUserInfo()

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
        try {
            if (response.profilePicturePath==null){
                Log.d("SSSSSSSSSSSSSSSSSSSSSSSSS",response.profilePicturePath.toString())
                profilePicturePathString=""
            }else{

                profilePicturePathString=response.profilePicturePath.toString()
            }
            if (response.age==null){
                Log.d("SSSSSSSSSSSSSSSSSSSSSSSSS",response.age.toString())
                ageString=0
            }else{

                ageString=response.age as Int
            }

            if (response.branchId==null){
                Log.d("SSSSSSSSSSSSSSSSSSSSSSSSS",response.branchId.toString())
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
                    profilePicturePathString,ageString,branchIdString

//
//,response.age!!,response.branchId

            )
            common.session!!.createLoginSession(userInfo)
            Log.d("createLoginSession2",common.session!!.createLoginSession(userInfo).toString())
            userInfo= UserInfoModel()
//        common.session!!.createLoginSession(userConfig)
            val mainIntent = Intent(applicationContext, MainActivity::class.java)
            startActivity(mainIntent)
            finish()
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

    override fun didAddSuccess(response: AvatarParser) {

    }

    override fun didAddFail(msg: String) {
    }
}