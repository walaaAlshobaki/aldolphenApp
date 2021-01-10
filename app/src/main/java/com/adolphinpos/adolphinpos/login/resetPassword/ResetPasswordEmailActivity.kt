package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import kotlinx.android.synthetic.main.activity_reset_password_email.*

class ResetPasswordEmailActivity : AppCompatActivity() ,ResetPasswordDelegate{
    var mPresenter: ResetPasswordPresenter? = null
    var code:String=""
    var email:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_password_email)
        mPresenter = ResetPasswordPresenter(this)
        mPresenter!!.delegate = this
        val bundle = intent.extras
        email=bundle!!.getString("email").toString()
        code=bundle!!.getString("code").toString()
        loginBtn.setOnClickListener {
            if (bundle != null) {
                email=bundle.getString("email").toString()
                if (!bundle.getString("email").isNullOrEmpty()) {
                    mPresenter!!.sendEmailTap(email,password.text.toString(),code)

                }
            }

        }
    }

    override fun didSendSuccess(token: String) {
        Toast.makeText(this@ResetPasswordEmailActivity, "the Password reset successfully", Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext, LoadingScreenActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun didSendFail(msg: String) {
        Toast.makeText(this@ResetPasswordEmailActivity, msg, Toast.LENGTH_LONG).show()

    }
}