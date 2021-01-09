package com.adolphinpos.adolphinpos.registeration.code

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordPresenter
import kotlinx.android.synthetic.main.activity_verification_screen.*

class VerificationScreenActivity : AppCompatActivity(),SendVerificationCodeDelegate {
    var mPresenter: SendVerificationCodePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen)
        mPresenter = SendVerificationCodePresenter(this)
        mPresenter!!.delegate = this
        sendSMS.setOnClickListener{
            mPresenter!!.senCode()
            val i = Intent(this, VerificationScreenCodeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }

    override fun didSendVerificationCodeSuccess(token: String) {

    }

    override fun didSendVerificationCodeFail(msg: String) {

    }
}