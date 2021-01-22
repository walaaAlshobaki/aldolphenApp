package com.adolphinpos.adolphinpos.plan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.registeration.code.VerificationScreenActivity
import com.adolphinpos.adolphinpos.registeration.register.RegisterActivity
import kotlinx.android.synthetic.main.activity_plan.*

class PlanActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_plan)
        free.setOnClickListener {
            val intent = Intent(applicationContext, VerificationScreenActivity::class.java)
            startActivity(intent)
            finish()
        }


        sign.setOnClickListener {
            val intent = Intent(applicationContext, RegisterActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}