package com.adolphinpos.adolphinpos.registeration.code

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordPresenter
import com.adolphinpos.adolphinpos.plan.PlanActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_edit_number.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.flagphone
import kotlinx.android.synthetic.main.activity_verification_screen.*

class VerificationScreenActivity : AppCompatActivity(),SendVerificationCodeDelegate {
    var mPresenter: SendVerificationCodePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen)
        mPresenter = SendVerificationCodePresenter(this)
        mPresenter!!.delegate = this
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 20) {
                textTitle2.text="will send you a code to "+  it.message
                whatsapptextTitle2.text="will send you a code to "+  it.message



            }
        }
//        val bundle = intent.extras
        textTitle2.text="will send you a code to "+ common.userPhone
        whatsapptextTitle2.text="will send you a code to "+ common.userPhone
        back.setOnClickListener {
            val intent = Intent(applicationContext, PlanActivity::class.java)
            startActivity(intent)
            finish()
        }


        sendSMS.setOnClickListener{
            mPresenter!!.senCode()

        }
        different.setOnClickListener{
            val intent = Intent(applicationContext, EditNumberActivity::class.java)
            startActivity(intent)
            finish()

        }

    }

    override fun didSendVerificationCodeSuccess(token: String) {
        DesignerToast.Custom(this,"the code send successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        val i = Intent(this, VerificationScreenCodeActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun didSendVerificationCodeFail(msg: String) {
        if (msg=="LastCodeNotExpired" || msg=="CodeSent"){
            DesignerToast.Custom(this,msg , Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219)
            val i = Intent(this, VerificationScreenCodeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else{
            DesignerToast.Custom(this,msg,Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }


    }
}