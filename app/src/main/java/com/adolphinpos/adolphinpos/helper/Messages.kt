package com.adolphinpos.adolphinpos.helper

import android.app.Activity
import com.adolphinpos.adolphinpos.R
import com.tapadoo.alerter.Alerter


class Alert(){


    companion object {

        val Instance = Alert()

    }

    fun showMessage(baseActivity: Activity, str:Int, isprogress:Boolean=false, icon:Int= R.drawable.ic_error){

        Alerter.hide()

        Alerter.create(baseActivity)

            .setText(str)
            .enableProgress(isprogress)
            .setBackgroundColorRes(R.color.appMainColor) // or setBackgroundColorInt(Color.CYAN)
            .setProgressColorRes(R.color.white)
            .setIcon(icon)

            .show()

    }


    fun showMessage(baseActivity: Activity, str:String){
        Alerter.hide()
        Alerter.create(baseActivity)

            .setText(str)

            .setBackgroundColorRes(R.color.appMainColor) // or setBackgroundColorInt(Color.CYAN)
            .setProgressColorRes(R.color.white)


            .show()

    }



    fun showMessageCorrect(baseActivity: Activity, str:String){
        Alerter.hide()
        Alerter.create(baseActivity)

            .setText(str)

            .setBackgroundColorRes(R.color.appColor) // or setBackgroundColorInt(Color.CYAN)
            .setProgressColorRes(R.color.appMainColor)


            .setIcon(R.drawable.ic_success)
            .show()

    }



    fun showMessageError(baseActivity: Activity, str:String){
        Alerter.hide()
        Alerter.create(baseActivity)

            .setText(str)

            .setBackgroundColorRes(R.color.red) // or setBackgroundColorInt(Color.CYAN)
            .setProgressColorRes(R.color.white)
            .setIcon(R.drawable.ic_error)


            .show()

    }

}