package com.adolphinpos.adolphinpos.login

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordActivity
import kotlinx.android.synthetic.main.activity_loading_screen.*


class LoadingScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_screen)
        Handler().postDelayed({ /* Create an Intent that will start the Menu-Activity. */
            val mainIntent = Intent(applicationContext, ForgetPasswordActivity::class.java)
           startActivity(mainIntent)
         finish()
        }, 1500)

    }
}