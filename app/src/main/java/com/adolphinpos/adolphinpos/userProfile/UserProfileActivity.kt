package com.adolphinpos.adolphinpos.userProfile

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.squareup.picasso.MemoryPolicy
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_user_profile.*

class UserProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
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


        FirstName.setText(userInfo.firstName)
        lastName.setText(userInfo.lastName)
        phoneNum.setText(userInfo.phoneNumber)
        email.setText(userInfo.email)


        update_layer.visibility = View.GONE

        update_active_btn.setOnClickListener {

            update_layer.visibility = View.VISIBLE
            toggle_editText(true)

        }

    }
    private fun toggle_editText(isActive: Boolean) {


        FirstName.isEnabled = isActive

        lastName.isEnabled = isActive
        phoneNum.isEnabled = isActive
        email.isEnabled = isActive


    }
}
