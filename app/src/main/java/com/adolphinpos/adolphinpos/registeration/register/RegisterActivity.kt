package com.adolphinpos.adolphinpos.registeration.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.helper.UserConfig
import com.adolphinpos.adolphinpos.login.userModel
import com.adolphinpos.adolphinpos.registeration.code.VerificationScreenActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(),RegisterationDelegate {
    var countryModel: CountryModel.Data? =null
    var mPresenter: RegisterationPresenter? = null
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
            }
        }
//        country.setEnabled(false)
        CountryTextInputLayout.isFocusable = true
        CountryTextInputLayout.isClickable = true
        country.setOnClickListener{
            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        loginBtn.setOnClickListener{
            mPresenter!!.RegisterTap(companyName.text.toString(),firstname.text.toString(),lastname.text.toString(),
                countryModel!!.id,email.text.toString(),phoneNum.text.toString(),password.text.toString())
//            RxBus.listen(MessageEvent::class.java).subscribe {
//                if (it.action == 1) {
//                    countryModel = it.message as CountryModel.Data
//
//                }
//            }
//
        }
    }
    fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)
    override fun didRegisterationSuccess(token: userModel, auth_token:String) {
        userConfig = UserConfig(token.firstName,token.lastName,"jo",token.userId.toString(),token.phoneNumber,token.email,auth_token)
        common.session!!.createLoginSession(userConfig)
//        common.userToken=token
//        common.userEmail=email.text.toString()
        Log.d("RRRRRRRRRRRRRR",token.firstName.toString())
        common.session!!.createLoginSession(userConfig)
        common.userToken=auth_token
        val i = Intent(this, VerificationScreenActivity::class.java)
        intent.putExtra("auth_token",auth_token)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }

    override fun didRegisterationFail(msg: String) {
        Log.d("didRegisterationFail",msg)
        Toast.makeText(this@RegisterActivity, msg, Toast.LENGTH_LONG).show()
    }

}