package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import com.adolphinpos.adolphinpos.login.LoginPresenter
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity(),ForgetPasswordDelegate {
    var mPresenter: ForgetPasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        mPresenter = ForgetPasswordPresenter(this)
        mPresenter!!.delegate = this
        resetBtn.setOnClickListener{
            mPresenter!!.sendEmailTap(email.text.toString())
        }
    }

    override fun didSendSuccess(token: String) {
        Toast.makeText(this@ForgetPasswordActivity, "the Email send successfully", Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
        intent.putExtra("email",email.text.toString())
        startActivity(intent)
    }

    override fun didSendFail(msg: String) {
        Toast.makeText(this@ForgetPasswordActivity, msg, Toast.LENGTH_LONG).show()
        if (msg == "LastEmailNotExpired"){
            val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
            intent.putExtra("email",email.text.toString())
            startActivity(intent)
        }

    }
}