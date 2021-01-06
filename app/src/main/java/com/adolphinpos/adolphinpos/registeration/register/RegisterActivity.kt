package com.adolphinpos.adolphinpos.registeration.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.registeration.code.VerificationScreenActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
//        country.setEnabled(false)

        CountryTextInputLayout.isFocusable = true
        CountryTextInputLayout.isClickable = true
        country.setOnClickListener{
            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK



            startActivity(i)
        }
        loginBtn.setOnClickListener{
            val i = Intent(this, VerificationScreenActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }
}