package com.adolphinpos.adolphinpos.login

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordActivity
import com.adolphinpos.adolphinpos.login.resetPassword.ResetPasswordEmailActivity
import com.adolphinpos.adolphinpos.registeration.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        Register.setOnClickListener{
            val i = Intent(this, RegisterActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        forgetPassword.setOnClickListener{
            val i = Intent(this, ResetPasswordEmailActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }

//        if (isTablet(this)){
//            setMargins(  findViewById<ConstraintLayout>(R.id.container), 100, 0, 100, 0)
//        }
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
}