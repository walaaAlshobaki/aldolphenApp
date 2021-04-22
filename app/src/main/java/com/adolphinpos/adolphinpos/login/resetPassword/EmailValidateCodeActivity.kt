package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.OtpEditText
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_email_validate_code.*
import kotlinx.android.synthetic.main.activity_verification_screen_code.loginBtn

class EmailValidateCodeActivity : AppCompatActivity(),EmailValidateDelegate {
    var mPresenter: EmailValidatePresenter? = null
    var code:String=""
    var email:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_validate_code)
        mPresenter = EmailValidatePresenter(this)
        mPresenter!!.delegate = this
        val txtPinEntry: OtpEditText =
            findViewById<View>(R.id.txt_pin_entry) as OtpEditText
        val bundle = intent.extras
        back.setOnClickListener {
            val intent = Intent(applicationContext, ForgetPasswordActivity::class.java)
            startActivity(intent)
        }
        txtPinEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                code=s.toString()
//                if (s.toString() == "123456") {
//                    Toast.makeText(this@VerificationScreenCodeActivity, "Success", Toast.LENGTH_SHORT).show()
//                } else if (s.length == "123456".length) {
//                    Toast.makeText(this@VerificationScreenCodeActivity, "Incorrect", Toast.LENGTH_SHORT).show()
//                    txtPinEntry.setText(null)
//                }
            }
        })
        loginBtn.setOnClickListener {
            if (bundle != null) {
                email=bundle.getString("email").toString()
                if (!bundle.getString("email").isNullOrEmpty()) {
                    mPresenter!!.emailValidate(bundle.getString("email").toString(),code)

                }
                }

        }
    }



    override fun didEmailValidateSuccess(token: EmailVlidateModel) {
        DesignerToast.Custom(this,"the Email Validate successfully", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        Toast.makeText(this@EmailValidateCodeActivity, "the Email send successfully", Toast.LENGTH_LONG).show()
        val intent = Intent(applicationContext, ResetPasswordEmailActivity::class.java)

        intent.putExtra("email",email)
        intent.putExtra("code",code)
        startActivity(intent)
        finish()
    }

    override fun didEmailValidateFail(msg: String) {
        if (msg=="null"){
            DesignerToast.Custom(this,"the Email Validate successfully", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
            Toast.makeText(this@EmailValidateCodeActivity, "the Email send successfully", Toast.LENGTH_LONG).show()
            val intent = Intent(applicationContext, ResetPasswordEmailActivity::class.java)

            intent.putExtra("email",email)
            intent.putExtra("code",code)
            startActivity(intent)
            finish()
        }else{


        DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
            R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }
    }
}