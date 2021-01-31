package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import com.adolphinpos.adolphinpos.login.LoginActivity
import com.adolphinpos.adolphinpos.login.LoginPresenter
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_forget_password.*

class ForgetPasswordActivity : AppCompatActivity(),ForgetPasswordDelegate {
    var mPresenter: ForgetPasswordPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        mPresenter = ForgetPasswordPresenter(this)
        mPresenter!!.delegate = this

        back.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)

            startActivity(intent)
        }
        resetBtn.setOnClickListener{
            mPresenter!!.sendEmailTap(email.text.toString())
        }
    }

    override fun didSendSuccess(token: String) {
        DesignerToast.Custom(this,"the Email send successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

        val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
        intent.putExtra("email",email.text.toString())
        startActivity(intent)
    }

    override fun didSendFail(msg: String) {
        if (msg == "EmailNotExpired"){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
            intent.putExtra("email",email.text.toString())
            startActivity(intent)
        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }

    }
}