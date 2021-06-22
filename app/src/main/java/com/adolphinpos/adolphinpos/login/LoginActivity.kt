
package com.adolphinpos.adolphinpos.login

import LoginDelegate
import LoginPresenter
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.CompoundButton
import android.widget.RadioButton
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.Adapters.SlidingImagemain_Adapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.resetPassword.ForgetPasswordActivity
import com.adolphinpos.adolphinpos.registeration.register.RegisterActivity
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.banner_slider.*
import kotlinx.android.synthetic.main.payment_item_cell.view.*


class LoginActivity : AppCompatActivity(),LoginDelegate {
    var mPresenter: LoginPresenter? = null
    var i = 0
    var wasChecked = false
    private var handler: Handler = Handler()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        mPresenter = LoginPresenter(this)


        mPresenter!!.delegate = this
        initListiner()
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
//        radioButton.isSelected=true
//        radioButton.setOnClickListener {
//            if (radioButton.isChecked) {
//                test.clearCheck()
//                radioButton.isChecked = false
////                radioButton.isSelected=false
////                radioButton.isEnabled=false
////                radioButton.isActivated=false
//
//                radioButton.setChecked(false);
//            } else  {
////                test.clearCheck()
//    test.check(R.id.radioButton)
//                radioButton.isChecked = true
////                radioButton.isSelected=true
////                radioButton.isEnabled=true
////                radioButton.isActivated=true
//                radioButton.setChecked(true);
//            }
//
//        }





    }
//     fun  onRadioButtonClicked(view: View) {
//        // Is the view now checked?
//        var checked:Boolean = ( view as RadioButton).isChecked();
//         Log.d("########################", checked.toString())
//        // Check which RadioButton was clicked
//        when(view.getId()) {
//            R.id.radioButton ->
//                if (checked) {
//                    test.clearCheck()
//                    // Do your coding
//                    radioButton.isChecked = false
//                } else {
//                    test.clearCheck()
//                    radioButton.isChecked = true
//                }
//            // Do your coding
//
//
//            // Perform your logic
//        }
//    }

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
    fun OnRadioButtonClick(view: View) {
        val checked = (view as RadioButton).isChecked
        if (checked) {
            (view as RadioButton).isChecked = false

        } else if (!checked) {

            (view as RadioButton).isChecked = true
        }

        // ...
    }
    fun initListiner() {
        registerText.setOnClickListener{
            val i = Intent(this, RegisterActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        Register.setOnClickListener{

            val i = Intent(this, RegisterActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        forgetPassword.setOnClickListener{
            val i = Intent(this, ForgetPasswordActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        loginBtn.setOnClickListener {
            if (password.text.toString().isNullOrEmpty()&&email.text.toString().isNullOrEmpty()){
                passwordTextInputLayout.error = "the password is required"
                userNameTextInputLayout.error = "the email is required"
                DesignerToast.Custom(
                    this,
                    "the email and password is required",
                    Gravity.TOP or Gravity.RIGHT,
                    Toast.LENGTH_LONG,
                    R.drawable.warnings_background,
                    16,
                    "#FFFFFF",
                    R.drawable.ic_warninges,
                    55,
                    219
                )
//                DesignerToast.Warning(this, "the email and is required", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG)

            }else  if (password.text.toString().isNullOrEmpty()){
//                DesignerToast.Warning(this, "the password is required", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG)
                DesignerToast.Custom(
                    this,
                    "the password is required",
                    Gravity.TOP or Gravity.RIGHT,
                    Toast.LENGTH_LONG,
                    R.drawable.warnings_background,
                    16,
                    "#FFFFFF",
                    R.drawable.ic_warninges,
                    55,
                    219
                );
                passwordTextInputLayout.error = "the password is required"
                userNameTextInputLayout.error = null
            }else if(email.text.toString().isNullOrEmpty()){
                passwordTextInputLayout.error = null
                DesignerToast.Custom(
                    this, "the email is required", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                    R.drawable.warnings_background, 16, "#FFFFFF", R.drawable.ic_warninges, 55, 219
                );
                userNameTextInputLayout.error = "the email is required"
            }else{
                mPresenter!!.loginTap(
                    email.text.toString(),
                    password.text.toString(),

                    )
            }

        }
    }

    override fun didLoginSuccess(auth_token: String) {
        userConfig = UserConfig(
            "token.firstName",
            "token.lastName",
            "jo",
            "1",
            "token.phoneNumber",
            "token.email",
            auth_token
        )
        userInfo.token=auth_token
        DesignerToast.Custom(
            this, "successfully login", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background, 16, "#FFFFFF", R.drawable.ic_checked, 55, 219
        )
        val intent = Intent(applicationContext, LoadingScreenActivity::class.java)
        intent.putExtra("action","login")
        startActivity(intent)
        finish()
    }

    override fun didLoginFail(msg: String) {
        if (msg=="true" || msg=="Login"){

        }else{
            DesignerToast.Custom(
                this, msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.erroe_background, 16, "#FFFFFF", R.drawable.ic_cancel1, 55, 219
            )
            Log.d("ttttttttttttttttttttttt", msg)
        }

    }

}