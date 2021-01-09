package com.adolphinpos.adolphinpos.login

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.adolphinpos.adolphinpos.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordActivity
import com.adolphinpos.adolphinpos.login.resetPassword.ResetPasswordEmailActivity
import com.adolphinpos.adolphinpos.registeration.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity(),LoginDelegate {
    var mPresenter: LoginPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this)


        mPresenter!!.delegate = this
        initListiner()


//        if (isTablet(this)){
//            setMargins(  findViewById<ConstraintLayout>(R.id.container), 100, 0, 100, 0)
//        }
    }
    fun initListiner() {
        Register.setOnClickListener{
            val i = Intent(this, RegisterActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        forgetPassword.setOnClickListener{
            val i = Intent(this, ForgetPasswordActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        loginBtn.setOnClickListener {
            mPresenter!!.loginTap(
                email.text.toString(),
                password.text.toString(),

            )
        }
    }
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.getLayoutParams() is MarginLayoutParams) {
            val p = v.getLayoutParams() as MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }
    fun isTablet(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }

    override fun didLoginSuccess(token: userModel,auth_token:String) {
       userConfig = UserConfig(token.firstName,token.lastName,"jo",token.userId.toString(),token.phoneNumber,token.email,auth_token)
       common.session!!.createLoginSession(userConfig)
//        common.userToken=token
//        common.userEmail=email.text.toString()
        Log.d("RRRRRRRRRRRRRR",token.firstName.toString())
        common.session!!.createLoginSession(userConfig)
        val intent = Intent(applicationContext, LoadingScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun didLoginFail(msg: String) {
        Toast.makeText(this@LoginActivity, msg,Toast.LENGTH_LONG).show()
    }
}