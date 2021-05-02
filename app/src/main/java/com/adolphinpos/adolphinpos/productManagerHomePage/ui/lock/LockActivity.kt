package com.adolphinpos.adolphinpos.productManagerHomePage.ui.lock

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.helper.Alert
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockListener
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_lock.*
import kotlinx.android.synthetic.main.alert.view.*

class LockActivity : AppCompatActivity() {
    val TAG = "PinLockView"


    override fun onBackPressed() {

        if (common.isLock==true){
           Log.d("LLLLLLLLLLLLLLLL","LOCKED")
        }else{

            this.onBackPressed()
        }
    }
    private val mPinLockListener: PinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {
            Log.d(TAG, "Pin complete: $pin")
            if (pin=="123456"){
                common.isLock=false
                finish()
            }
        }

        override fun onEmpty() {
            Log.d(TAG, "Pin empty")
        }

        override fun onPinChange(pinLength: Int, intermediatePin: String) {
            Log.d(TAG, "Pin changed, new length $pinLength with intermediate pin $intermediatePin")
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        );
        setContentView(R.layout.activity_lock)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
       pin_lock_view!!.attachIndicatorDots(indicator_dots)
         pin_lock_view!!.setPinLockListener(mPinLockListener);

        common.isLock=true
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();

         pin_lock_view.setPinLength(6);
        pin_lock_view.setTextColor(ContextCompat.getColor(this, R.color.appMainColor))
        pin_lock_view.buttonBackgroundDrawable=resources.getDrawable(R.drawable.circle_layer)
        pin_lock_view.resetPinLockView()
        pin_lock_view.isShowDeleteButton=true
        pin_lock_view.deleteButtonDrawable=resources.getDrawable(R.drawable.ic_delete2)


        indicator_dots.setIndicatorType(IndicatorDots.IndicatorType.FIXED)


        logout.setOnClickListener {
            doLogout()
        }

    }

    fun doLogout() {


        Alerter.create(this, R.layout.alert)
            .setDuration(1000000)

            .setBackgroundColorRes(R.color.appColor)
            .also { alerter ->

                val container = alerter.getLayoutContainer()!!.rootView
                container.tvCustomLayout.text = resources.getString(R.string.logoutmsg)

                container.ok.setOnClickListener {
                    Alert.Instance.showMessage(this as Activity,R.string.logout,true)
                    Alerter.hide()
                    common.session!!.logoutUser()



                    val i = this!!.packageManager
                        .getLaunchIntentForPackage(this!!.packageName)
                    i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                    finish()

                }

                container.no.setOnClickListener {
                    Alerter.hide()

                }
            }
            .show()






    }
}