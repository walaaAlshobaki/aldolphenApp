package com.adolphinpos.adolphinpos.registeration.code

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.registeration.country.CountryActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import com.ahmadrosid.svgloader.SvgLoader
import kotlinx.android.synthetic.main.activity_edit_number.*
import kotlinx.android.synthetic.main.activity_edit_number.flagphone
import kotlinx.android.synthetic.main.activity_edit_number.phoneNum



class EditNumberActivity : AppCompatActivity() {
    var countryModel: CountryModel.Data? =null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_number)
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 1) {
                countryModel = it.message as CountryModel.Data
                SvgLoader.pluck()
                    .with(this as Activity?)
                    .setPlaceHolder(R.drawable.ca,R.drawable.ca)
                    .load(countryModel!!.flag, flagphone)

            }
        }
        phoneNum.setOnClickListener{

            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        close.setOnClickListener{

           finish()

        }

        flagphone.setOnClickListener{
            val i = Intent(this, CountryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
        confirmBtn.setOnClickListener{
            RxBus.publish(MessageEvent(20, "00"+countryModel!!.callingCodes+phoneNum.text.toString()))
            onBackPressed()
        }
    }
}