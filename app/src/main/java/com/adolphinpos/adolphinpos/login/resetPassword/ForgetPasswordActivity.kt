package com.adolphinpos.adolphinpos.login.resetPassword

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.Adapters.SlidingImagemain_Adapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import com.adolphinpos.adolphinpos.login.LoginActivity

import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_forget_password.*
import kotlinx.android.synthetic.main.banner_slider.*

class ForgetPasswordActivity : AppCompatActivity(),ForgetPasswordDelegate {
    var mPresenter: ForgetPasswordPresenter? = null
    var i = 0
    private var handler: Handler = Handler()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)
        mPresenter = ForgetPasswordPresenter(this)
        mPresenter!!.delegate = this

        back.setOnClickListener {
            val intent = Intent(applicationContext, LoginActivity::class.java)

            startActivity(intent)
        }
        resetBtn.setOnClickListener{
            mPresenter!!.sendEmailTap(email.text.toString())
        }

        val myImageList: ArrayList<Int> = ArrayList()
        myImageList.add(R.drawable.image2)
        myImageList.add(R.drawable.image1)
        myImageList.add(R.drawable.image3)
        myImageList.add(R.drawable.image4)
        myImageList.add(R.drawable.image5)
        myImageList.add(R.drawable.image6)
        mPager!!.adapter = SlidingImagemain_Adapter(this, myImageList)
        mPager!!.isNestedScrollingEnabled = true
        indicatiormain.setupWithViewPager(mPager, true);

        autoSlider(mPager)
    }


    fun autoSlider(viewPager: ViewPager) {
        try {


            var rr = Runnable {
                try {
                    val pos = viewPager.currentItem
                    if (pos > i && pos != mPager!!.adapter!!.count - 1) {
                        i = pos
                        i++
                    } else if (pos < i - 1) {
                        i = pos
                        i++
                    }
                    viewPager.setCurrentItem(i, true)
                    i++
                    if (mPager != null)
                        if (i >= mPager!!.adapter!!.count) i = 0
                    autoSlider(viewPager)
                } catch (ex: Exception) {


                }
            }
            handler.postDelayed(rr, 3000)

        } catch (ex: Exception) {


        }
    }
    override fun didSendSuccess(token: String) {
        DesignerToast.Custom(this,"the Email send successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

        val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
        intent.putExtra("email",email.text.toString())
        startActivity(intent)
    }

    override fun didSendFail(msg: String) {
        if (msg == "EmailNotExpired"||msg == "EmailSent"){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            val intent = Intent(applicationContext, EmailValidateCodeActivity::class.java)
            intent.putExtra("email",email.text.toString())
            startActivity(intent)
        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }

    }
}