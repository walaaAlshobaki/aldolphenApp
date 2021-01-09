package com.adolphinpos.adolphinpos.registeration.code

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.MainActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.OtpEditText
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import kotlinx.android.synthetic.main.activity_verification_screen_code.*


class VerificationScreenCodeActivity : AppCompatActivity() ,ValidateCodeDelegate{
    var mPresenter: ValidateCodePresenter? = null
    var code:String=""
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen_code)
        mPresenter = ValidateCodePresenter(this)
        mPresenter!!.delegate = this
        val txtPinEntry: OtpEditText =
            findViewById<View>(R.id.txt_pin_entry) as OtpEditText
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
            mPresenter!!.getCode(code)
            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun didGetValidateCodeSuccess(response: CodePresenter) {
Log.d("didGetValidateCodeSuccess","WWWWWWWWWWWWWWWWWWWWWWWWWW")
    }

    override fun didGetValidateCodeFail(msg: String) {

    }

    override fun didEmpty() {

    }
}