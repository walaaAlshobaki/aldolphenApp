package com.adolphinpos.adolphinpos.companyProfile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.MainActivity
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*

class CompanyProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_company_profile)
        sign.setOnClickListener {
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }
        initData()
        Picasso.get()
            .load(R.drawable.user)
            .error(R.drawable.user)
            .transform(CircleTransform())
            .memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE)
            .into(avatar_img)
    }

private fun initData() {

    Picasso.get().load(R.drawable.user).error(R.drawable.user).transform(CircleTransform()).into(userImage)
    Picasso.get().load(R.drawable.user).error(R.drawable.user).transform(CircleTransform()).into(avatar_img)
    userName.text= userInfo.firstName +" "+ userInfo.lastName
    firstname.setText(userInfo.firstName)
    lastname.setText(userInfo.lastName)
    phoneNum.setText(userInfo.phoneNumber)
    email.setText(userInfo.email)
    age.setText("0")

//        mPresenter!!.getUserInfo()
    update_layer.visibility = View.GONE

    update_active_btn.setOnClickListener {

        update_layer.visibility = View.VISIBLE
        toggle_editText(true)

    }


}

    private fun toggle_editText(isActive: Boolean) {


        firstname.isEnabled = isActive

        lastname.isEnabled = isActive
        phoneNum.isEnabled = isActive
        email.isEnabled = isActive


    }
}