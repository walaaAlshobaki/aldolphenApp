package com.adolphinpos.adolphinpos.registeration.register

import android.app.Activity
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.Adapters.SlidingImagemain_Adapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.LoginActivity
import com.adolphinpos.adolphinpos.login.userModel
import com.adolphinpos.adolphinpos.registeration.code.VerificationScreenActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.activity_register.email
import kotlinx.android.synthetic.main.activity_register.emailError
import kotlinx.android.synthetic.main.activity_register.loginBtn
import kotlinx.android.synthetic.main.activity_register.password
import kotlinx.android.synthetic.main.activity_register.passwordTextInputLayout
import kotlinx.android.synthetic.main.banner_slider.*

class RegisterActivity : AppCompatActivity(),RegisterationDelegate {
    var countryModel: CountryModel.Data? =null
    var mPresenter: RegisterationPresenter? = null
    var i = 0
    private var handler: Handler = Handler()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterationPresenter(this)
        mPresenter!!.delegate = this
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
                country.text= countryModel!!.name.toEditable()
                SvgLoader.pluck()
                    .with(this as Activity?)
                    .setPlaceHolder(R.drawable.ca,R.drawable.ca)
                    .load(countryModel!!.flag, flag)

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
        registerText.text="login"
        slidinfo.text="if you have an account here!"
        registerText.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        autoSlider(mPager)
//        country.setEnabled(false)
        CountryTextInputLayout.isFocusable = true
        CountryTextInputLayout.isClickable = true
        country.setOnClickListener{
            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        sign.setOnClickListener{
            val i = Intent(this, LoginActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        loginBtn.setOnClickListener{
            if (companyName.text.isNullOrEmpty()||firstname.text.isNullOrEmpty()||lastname.text.isNullOrEmpty()|| countryModel!!.id==0||email.text.isNullOrEmpty()||phoneNum.text.isNullOrEmpty()||password.text.isNullOrEmpty()){
                DesignerToast.Custom(this,"All filed is required",Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                    R.drawable.warnings_background,16,"#FFFFFF",R.drawable.ic_warninges, 55, 219);
            }else{
//                RxBus.publish(MessageEvent(4, countryModel!!.callingCodes+phoneNum.text.toString()))
//                Log.d("WWWWWWWWWWWWWWWWWWWWWWWWWW",countryModel!!.callingCodes+phoneNum.text.toString())

                mPresenter!!.RegisterTap(companyName.text.toString(),firstname.text.toString(),lastname.text.toString(),
                    countryModel!!.id,email.text.toString(),countryModel!!.callingCodes+phoneNum.text.toString(),password.text.toString())
            }

//            RxBus.listen(MessageEvent::class.java).subscribe {
//                if (it.action == 1) {
//                    countryModel = it.message as CountryModel.Data
//
//                }
//            }
//
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
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    override fun didRegisterationSuccess(token: userModel, auth_token:String) {
        userConfig = UserConfig(
                "token.firstName",
                "token.lastName",
                "jo",
                token.userId.toString(),
                "token.phoneNumber",
                "token.email",
                auth_token
        )
//        common.session!!.createLoginSession(userConfig)
//        common.userToken=token
//        common.userEmail=email.text.toString()
//        Log.d("RRRRRRRRRRRRRR",token.firstName.toString())
//        common.session!!.createLoginSession(userConfig)
        common.userToken=auth_token
        DesignerToast.Custom(this,"successfully registration",Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        val i = Intent(this, VerificationScreenActivity::class.java)
        i.putExtra("auth_token",auth_token)
        i.putExtra("mobile",countryModel!!.callingCodes+phoneNum.text.toString())
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun didRegisterationFail(msg: String) {
        Log.d("didRegisterationFail",msg)
        Log.d("ttttttttttttttttttttttt", msg)
        DesignerToast.Custom(this,msg, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
            R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        Toast.makeText(this@RegisterActivity, msg, Toast.LENGTH_LONG).show()
        if (msg.equals("Email is used")){
            EmailTextInputLayout.setBoxStrokeColor(resources.getColor(R.color.red))
            EmailTextInputLayout.error = msg
            PhoneTextInputLayout.error = null
            CompanyTextInputLayout.error = null
//            emailError.text=msg


        }else if (msg.equals("Phone Number is used")){
            PhoneTextInputLayout.setBoxStrokeColor(resources.getColor(R.color.red))
            PhoneTextInputLayout.error = msg

            EmailTextInputLayout.error = null
            CompanyTextInputLayout.error = null
//            phoneError.text=msg

        }else if (msg.equals("Coampny name is used")){
            CompanyTextInputLayout.setBoxStrokeColor(resources.getColor(R.color.red))
            CompanyTextInputLayout.error = msg
            EmailTextInputLayout.error = null
            PhoneTextInputLayout.error = null
//            companyNameError.text=msg

        }
    }

}