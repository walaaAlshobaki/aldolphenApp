package com.adolphinpos.adolphinpos.registeration.code

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_verification_screen.*

class VerificationScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen)
        sendSMS.setOnClickListener{
            val i = Intent(this, VerificationScreenCodeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }
}