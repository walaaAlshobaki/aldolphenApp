package com.adolphinpos.adolphinpos.productManagerHomePage.ui.lock

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.core.content.ContextCompat
import com.adolphinpos.adolphinpos.R
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockListener
import kotlinx.android.synthetic.main.activity_lock.*
import kotlinx.android.synthetic.main.fragment_lock.view.*

class LockActivity : AppCompatActivity() {
    val TAG = "PinLockView"



    private val mPinLockListener: PinLockListener = object : PinLockListener {
        override fun onComplete(pin: String) {
            Log.d(TAG, "Pin complete: $pin")
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
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_lock)

       pin_lock_view!!.attachIndicatorDots(indicator_dots)
         pin_lock_view!!.setPinLockListener(mPinLockListener);
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();

         pin_lock_view.setPinLength(6);
        pin_lock_view.setTextColor(ContextCompat.getColor(this, R.color.appMainColor))
        pin_lock_view.buttonBackgroundDrawable=resources.getDrawable(R.drawable.circle_layer)
        pin_lock_view.resetPinLockView()

        indicator_dots.setIndicatorType(IndicatorDots.IndicatorType.FIXED);

    }
}