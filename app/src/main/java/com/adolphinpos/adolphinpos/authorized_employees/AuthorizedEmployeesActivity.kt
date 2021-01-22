package com.adolphinpos.adolphinpos.authorized_employees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_authorized_employees.sign
import kotlinx.android.synthetic.main.activity_authorized_employees.userImage
import kotlinx.android.synthetic.main.activity_authorized_employees.userName

class AuthorizedEmployeesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorized_employees)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName


        sign.setOnClickListener {
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }


    }
}