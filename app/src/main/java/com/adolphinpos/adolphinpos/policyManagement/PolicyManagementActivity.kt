package com.adolphinpos.adolphinpos.policyManagement

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import kotlinx.android.synthetic.main.activity_policy_management.*

class PolicyManagementActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_policy_management)
        loginBtn.setOnClickListener {
            RxBus.publish(MessageEvent(0, PolicyName.text.toString()))

            onBackPressed()
        }

    }
}