package com.adolphinpos.adolphinpos.userProfile

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.MainActivity
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoDelegate
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoModel
import com.adolphinpos.adolphinpos.login.userInfo.UserInfoPresenter
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_pos_setting.*
import kotlinx.android.synthetic.main.activity_user_profile.*
import kotlinx.android.synthetic.main.activity_user_profile.sign
import kotlinx.android.synthetic.main.activity_user_profile.userImage
import kotlinx.android.synthetic.main.activity_user_profile.userName

class UserProfileActivity : AppCompatActivity() , UserInfoDelegate {
    var mPresenter: UserInfoPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        mPresenter = UserInfoPresenter(this)
        mPresenter!!.delegate = this

        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
        sign.setOnClickListener {
            val i = Intent(this, MainActivity::class.java)
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

    override fun didGetUserInfoSuccess(response: UserInfoModel) {
//        Picasso.get().load(response.profilePicturePath).error(R.drawable.user).placeholder(R.drawable.user).transform(CircleTransform()).into(userImage)
//        userName.text= response.firstName +" "+ response.lastName
        firstname.setText(response.firstName)
        lastname.setText(response.lastName)
        phoneNum.setText(response.phoneNumber)
        email.setText(response.email)
        age.setText("0")
    }

    override fun didGetUserInfoFail(msg: String) {

    }

    override fun didEmpty() {

    }
}
