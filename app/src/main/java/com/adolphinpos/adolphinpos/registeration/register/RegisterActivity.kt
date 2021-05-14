package com.adolphinpos.adolphinpos.registeration.register

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.Adapters.SlidingImagemain_Adapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.companyProfile.CompanyProfilePresenter
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.LoginActivity
import com.adolphinpos.adolphinpos.plan.PlanActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryDelegate
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.adolphinpos.adolphinpos.registeration.country.CountryPresenter
import com.ahmadrosid.svgloader.SvgLoader
import com.rilixtech.widget.countrycodepicker.CountryCodePicker.OnCountryChangeListener
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_register.*

import kotlinx.android.synthetic.main.banner_slider.*


class RegisterActivity : AppCompatActivity(),RegisterationDelegate, CountryDelegate {
    var countryModel: CountryModel.Data? =null
    var phoneCode=""
    var mPresenter: RegisterationPresenter? = null
    var i = 0
    private var handler: Handler = Handler()
    var mCountryPresenter: CountryPresenter? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        mPresenter = RegisterationPresenter(this)
        mPresenter!!.delegate = this
        mCountryPresenter = CountryPresenter(this)
        mCountryPresenter!!.delegate = this
        mCountryPresenter!!.getCountry()
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
//                    mPresenter!!.scheduleTap(day!!.format(formatted))
//                country.text= countryModel!!.name.toEditable()
//                SvgLoader.pluck()
//                    .with(this as Activity?)
//                    .setPlaceHolder(R.drawable.ca,R.drawable.ca)
//                    .load(countryModel!!.flag, flag)
//                SvgLoader.pluck()
//                    .with(this as Activity?)
//                    .setPlaceHolder(R.drawable.ca,R.drawable.ca)
//                    .load(countryModel!!.flag, flagphone)
//                callCode.text= "(+"+countryModel!!.callingCodes+")"

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
        phoneCode=countryCodePicker.selectedCountryCode
        countryCodePicker.setOnCountryChangeListener(OnCountryChangeListener { selectedCountry ->
            Log.d(

                "Updated" ,selectedCountry.name+"CODE"+selectedCountry.phoneCode,

            )
            phoneCode=selectedCountry.phoneCode
            countryCodePicker2.setCountryForNameCode(selectedCountry.phoneCode)
            mCountryPresenter!!.getCountry()
        })
        countryCodePicker2.setOnCountryChangeListener(OnCountryChangeListener { selectedCountry ->
            Log.d(

                "Updated" ,selectedCountry.name+"CODE"+selectedCountry.phoneCode,

            )
            phoneCode=selectedCountry.phoneCode
            countryCodePicker.setCountryForNameCode(selectedCountry.phoneCode)
            mCountryPresenter!!.getCountry()
        })

        Log.d(

            "Updated" ,countryCodePicker.selectedCountryName+"CODE  "+countryCodePicker.selectedCountryCode,

            )

//        country.setEnabled(false)
//        CountryTextInputLayout.isFocusable = true
//        CountryTextInputLayout.isClickable = true
//        country.setOnClickListener{
//
//              val i = Intent(this, CountryActivity::class.java)
//              i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//              startActivity(i)
//
//        }
//        code.setOnClickListener{
//
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//
//        }
//        phoneNum.setOnClickListener{
//
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//
//        }
//        flagcode.setOnClickListener{
//            val i = Intent(this, CountryActivity::class.java)
//            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
//            startActivity(i)
//        }
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
                 if(password.text.toString().equals(passwordConf.text.toString())){
                     mPresenter!!.RegisterTap(companyName.text.toString(),firstname.text.toString(),lastname.text.toString(),
                         countryModel!!.id,email.text.toString(),"00"+countryModel!!.callingCodes+phoneNum.text.toString(),password.text.toString())
                }else{
                     passwordTextInputLayout.error = "password is not matches"
                     ConfirmpasswordTextInputLayout.error = "password is not matches"
                 }
//                RxBus.publish(MessageEvent(4, countryModel!!.callingCodes+phoneNum.text.toString()))
//                Log.d("WWWWWWWWWWWWWWWWWWWWWWWWWW",countryModel!!.callingCodes+phoneNum.text.toString())


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
    override fun didRegisterationSuccess(token: String) {
        Log.d("didRegisterationSuccess", token)
        userConfig = UserConfig(
            "token.firstName",
            "token.lastName",
            "jo",
            "1",
            "token.phoneNumber",
            "token.email",
            token
        )
        userInfo.token=token
        common.userToken=token
//        DesignerToast.Custom(this,"successfully login",Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
//                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        val i = Intent(this, PlanActivity::class.java)
        i.putExtra("auth_token", token!!)
        i.putExtra("mobile",countryModel!!.callingCodes+phoneNum.text.toString())
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)

    }

    override fun didRegisterationFail(msg: String) {
        Log.d("didRegisterationFail", msg)
        Log.d("didRegisterationFail", msg + userInfo.token)
        Log.d("didRegisterationFail", msg + common.userToken)

        if (msg=="CompanyAdded"||msg=="true"){
            userConfig = UserConfig(
                "token.firstName",
                "token.lastName",
                "jo",
                "-1",
                "token.phoneNumber",
                "token.email",
                userInfo.token
            )
            userInfo.token= common.userToken!!
            common.userPhone=countryModel!!.callingCodes+phoneNum.text.toString()
            DesignerToast.Custom(
                this, "successfully registration", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219
            )
            val i = Intent(this, PlanActivity::class.java)
            i.putExtra("auth_token", userInfo.token!!)
//            i.putExtra("mobile","00"+countryModel!!.callingCodes+phoneNum.text.toString())
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else{

        DesignerToast.Custom(
            this, msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.erroe_background, 16, "#FFFFFF", R.drawable.ic_cancel1, 55, 219
        )
        Toast.makeText(this@RegisterActivity, msg, Toast.LENGTH_LONG).show()

        }

    }

    override fun didGetCountrySuccess(response: CountryModel) {
        Log.d("SSSSSSSSSSSSSSSS",userInfo.contryId.toString())
        val iterator = (response.data.indices).iterator()

        if (iterator.hasNext()) {
            iterator.next()
        }

// do something with the rest of elements
        iterator.forEach {

            if (response.data[it].callingCodes == phoneCode) {
                countryModel=response.data[it]
                Log.d("SSSSSSSSSSSSSSSS",response.data[it].toString())

            }




        }

    }

    override fun didGetCountryFail(msg: String) {

    }

    override fun didEmpty() {

    }

}