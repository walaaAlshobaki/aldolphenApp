package com.adolphinpos.adolphinpos.registeration.code

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.Adapters.SlidingImagemain_Adapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.OtpEditText
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.login.LoadingScreenActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_edit_number.*
import kotlinx.android.synthetic.main.activity_verification_screen_code.*
import kotlinx.android.synthetic.main.banner_slider.*
import java.util.*
import kotlin.collections.ArrayList


class VerificationScreenCodeActivity : AppCompatActivity() ,ValidateCodeDelegate,SendVerificationCodeDelegate,EditNumberDelegate{
    var mPresenter: ValidateCodePresenter? = null
    var code:String=""
    var i = 0
    var number: String? =null
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning = false
    private val START_TIME_IN_MILLIS: Long = 300000
    private var mTimeLeftInMillis: Long = START_TIME_IN_MILLIS
    private var handler: Handler = Handler()
    var SendVerificationPresenter: SendVerificationCodePresenter? = null
    var editNumberPresenter: EditNumberPresenter? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen_code)
        mPresenter = ValidateCodePresenter(this)
        SendVerificationPresenter = SendVerificationCodePresenter(this)
        mPresenter!!.delegate = this
        editNumberPresenter = EditNumberPresenter(this)
        editNumberPresenter!!.delegate = this
        SendVerificationPresenter!!.delegate = this
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 20) {
                number = it.message.toString()
                editNumberPresenter!!.editNumber(number!!)

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

        startTimer()
        differentNumber.setOnClickListener{
            val intent = Intent(applicationContext, EditNumberActivity::class.java)
            startActivity(intent)


        }
        reSend.setOnClickListener{
            SendVerificationPresenter!!.senCode()

        }
//        differentNumber.setOnClickListener{
//            editNumberPresenter!!.editNumber()
//
//        }

        val txtPinEntry: OtpEditText =
            findViewById<View>(R.id.txt_pin_entry) as OtpEditText
        txtPinEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                code = s.toString()

            }
        })
        loginBtn.setOnClickListener {
            mPresenter!!.getCode(code)

        }
    }



    private fun startTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
                reSend.visibility=View.VISIBLE
                differentNumber.visibility=View.VISIBLE
            }
        }.start()
        mTimerRunning = true

    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timeLeftFormatted: String = java.lang.String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        timertext.setText(timeLeftFormatted+" s")
    }
    fun autoSlider(viewPager: ViewPager) {
        try {


            val rr = Runnable {
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
    override fun didGetValidateCodeSuccess(response: String) {
        DesignerToast.Custom(this, "the code Validate successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219)
        val intent = Intent(applicationContext, LoadingScreenActivity::class.java)
        intent.putExtra("action","resgister")
        startActivity(intent)
        finish()
    }

    override fun didGetValidateCodeFail(msg: String) {
        Log.d("EEEEEEEEEEEEEE",msg)
        if (msg=="null"){
            DesignerToast.Custom(this, "the code Validate successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219)
            val intent = Intent(applicationContext, LoadingScreenActivity::class.java)
            intent.putExtra("action","resgister")
            startActivity(intent)
            finish()
        }else{
            DesignerToast.Custom(this, msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.erroe_background, 16, "#FFFFFF", R.drawable.ic_cancel1, 55, 219)
        }

    }

    override fun didEmpty() {

    }

    override fun didSendVerificationCodeSuccess(token: String) {
        DesignerToast.Custom(this,"the code send successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        startTimer()
    }

    override fun didSendVerificationCodeFail(msg: String) {
        if (msg=="CodeNotExpired" ){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            startTimer()
        }else  if (msg=="true"|| msg=="CodeSent"){

        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                    R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }
    }

    override fun didEditNumberSuccess(token: String) {
        DesignerToast.Custom(this,"the mobile number updated successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        SendVerificationPresenter!!.senCode()
    }

    override fun didEditNumberFail(msg: String) {
        if (msg=="Updated" ){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            SendVerificationPresenter!!.senCode()
        }else  if (msg=="true"){

        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                    R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }
    }
}