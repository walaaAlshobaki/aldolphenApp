package com.adolphinpos.adolphinpos.productManagerHomePage.ui.lock

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.adolphinpos.adolphinpos.R
import com.andrognito.pinlockview.IndicatorDots
import com.andrognito.pinlockview.PinLockListener
import com.andrognito.pinlockview.PinLockView
import kotlinx.android.synthetic.main.activity_lock.*
import kotlinx.android.synthetic.main.fragment_lock.view.*


class LockFragment : Fragment() {
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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root= inflater.inflate(R.layout.fragment_lock, container, false)

        root.pin_lock_view!!.attachIndicatorDots(root.indicator_dots)
        root. pin_lock_view!!.setPinLockListener(mPinLockListener);
        //mPinLockView.setCustomKeySet(new int[]{2, 3, 1, 5, 9, 6, 7, 0, 8, 4});
        //mPinLockView.enableLayoutShuffling();

        root. pin_lock_view.setPinLength(6);
        root. pin_lock_view.setTextColor(ContextCompat.getColor(requireActivity(), R.color.white))
        root.pin_lock_view.buttonBackgroundDrawable=resources.getDrawable(R.drawable.circle_layer)
        root. pin_lock_view.resetPinLockView()

        root. indicator_dots.setIndicatorType(IndicatorDots.IndicatorType.FIXED);

        return  root
    }


}