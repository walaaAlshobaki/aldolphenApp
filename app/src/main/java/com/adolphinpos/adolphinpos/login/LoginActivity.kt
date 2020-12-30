package com.adolphinpos.adolphinpos.login

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.MarginLayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.adolphinpos.adolphinpos.R


class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        if (isTablet(this)){
            setMargins(  findViewById<ConstraintLayout>(R.id.container), 100, 0, 100, 0)
        }
    }
    fun setMargins(v: View, l: Int, t: Int, r: Int, b: Int) {
        if (v.getLayoutParams() is MarginLayoutParams) {
            val p = v.getLayoutParams() as MarginLayoutParams
            p.setMargins(l, t, r, b)
            v.requestLayout()
        }
    }
    fun isTablet(context: Context): Boolean {
        return ((context.resources.configuration.screenLayout
                and Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE)
    }
}