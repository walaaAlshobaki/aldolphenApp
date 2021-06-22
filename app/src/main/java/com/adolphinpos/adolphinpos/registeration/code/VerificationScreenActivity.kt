package com.adolphinpos.adolphinpos.registeration.code

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
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.plan.PlanActivity
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_verification_screen.*
import kotlinx.android.synthetic.main.activity_verification_screen.back
import kotlinx.android.synthetic.main.banner_slider.*

class VerificationScreenActivity : AppCompatActivity(),SendVerificationCodeDelegate,EditNumberDelegate {
    var mPresenter: SendVerificationCodePresenter? = null
    var code:String=""
    var i = 0
    private var handler: Handler = Handler()
    var number: String? =null
    var editNumberPresenter: EditNumberPresenter? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen)
        mPresenter = SendVerificationCodePresenter(this)
        mPresenter!!.delegate = this
        editNumberPresenter = EditNumberPresenter(this)
        editNumberPresenter!!.delegate = this
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 20) {
                textTitle2.text="will send you a code to "+  it.message
                whatsapptextTitle2.text="will send you a code to "+  it.message
                common.userPhone=it.message.toString()


            }
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

//        val bundle = intent.extras
        textTitle2.text="will send you a code to "+ common.userPhone
        whatsapptextTitle2.text="will send you a code to "+ common.userPhone
        back.setOnClickListener {
         onBackPressed()
        }


        sendSMS.setOnClickListener{
            mPresenter!!.senCode()

        }
        different.setOnClickListener{
            val intent = Intent(applicationContext, EditNumberActivity::class.java)
            startActivity(intent)


        }

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
    override fun didSendVerificationCodeSuccess(token: String) {
        Log.d("TTTTTTTTTTTTTTTTTTTTTTT",token)
        common.codeTimer=5

        DesignerToast.Custom(this,"the code send successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        val i = Intent(this, VerificationScreenCodeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun didSendVerificationCodeFail(msg: String) {
        if (msg=="CodeNotExpired" ){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            val i = Intent(this, VerificationScreenCodeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else  if (msg=="true"|| msg=="CodeSent"){

        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }


    }

    override fun didEditNumberSuccess(token: String) {

    }

    override fun didEditNumberFail(msg: String) {

    }
}