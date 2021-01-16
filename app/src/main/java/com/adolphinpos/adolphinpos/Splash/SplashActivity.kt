package com.adolphinpos.adolphinpos.Splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.Common
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import com.adolphinpos.adolphinpos.login.LoginActivity
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.manhal.lms.app.Helper.SessionLoginCallBack
import com.manhal.lms.app.Helper.SessionManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_splash.*

var common: Common = Common()
var userConfig= UserConfig()
var userInfo= UserInfoModel()
class SplashActivity : AppCompatActivity() {
    private var isLogin=false
    private var mDelayHandler: Handler? = null
    private val mRunnable: Runnable = Runnable {
        if (!isFinishing) {

            if (isLogin) {

                val mainIntent = Intent(this@SplashActivity, MainActivity::class.java)
                this@SplashActivity.startActivity(mainIntent)
                this@SplashActivity.finish()

            }else{

                val intent = Intent(applicationContext, LoginActivity::class.java)
                startActivity(intent)
                finish()

            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Picasso.get().load("http://13.59.88.58:9000//assets/images/logos/logo.png").into(imageView15)
        userConfig=UserConfig()
        common = Common()
        //Initialize the Handler
        mDelayHandler = Handler()
        common.session = SessionManager(this)
        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, 2000)

        common.session!!.checkLogin(object : SessionLoginCallBack {
            @SuppressLint("LogNotTimber")
            override fun didLoginSuccess() {
                Log.d("checkLogin",common.session!!.getUserDetails().firstName)

                userInfo = common.session!!.getUserDetails()
                isLogin = true

//                common.schoolInfo!!.schoolDomain = userConfig.domain
//                serverManager.reinitApiUrl(common.schoolInfo!!.schoolDomain)


                //   Alert.Instance.showMessageError(this@splash,common.schoolInfo!!.schoolDomain + " " + userConfig.fullname)


                // Update AuthKey

//                refreshToken()


//                if (isHMSAvailable(this@splash)) {
////                    HmsMessaging.getInstance(this@splash).isAutoInitEnabled = true
//
//                    object : Thread() {
//                        @SuppressLint("LogNotTimber")
//                        override fun run() {
//                            try {
//                                // read from agconnect-services.json
//                                val appId = AGConnectServicesConfig.fromContext(this@splash)
//                                    .getString("client/app_id")
//                                val token = HmsInstanceId.getInstance(this@splash)
//                                    .getToken(appId, "HCM")
//                                Log.i(TAG, "get token:$token")
//                                if (!TextUtils.isEmpty(token)) {
//                                    refreshFireBaseToken(token)
//                                }
//
//                            } catch (e: ApiException) {
//                                Log.e(TAG,
//                                    "get token failed, $e"
//                                )
//
//                            }
//                        }
//                    }.start()
//
//                } else {
//                FirebaseInstanceId.getInstance().instanceId
//                    .addOnCompleteListener(OnCompleteListener { task ->
//                        if (!task.isSuccessful) {
//
//                            return@OnCompleteListener
//                        }
//
//                        // Get new Instance ID token
//                        val token = task.result!!.token
//
//                        Log.d("TOKENFCM", token)
//
//                        refreshFireBaseToken(token)
//
//                    })

            }
//            }

            override fun didLoginFail() {

                // showMessageError(this@splash,"not log in")
                isLogin = false

            }


        })
        /* Create an Intent that will start the Menu-Activity. */


    }
}