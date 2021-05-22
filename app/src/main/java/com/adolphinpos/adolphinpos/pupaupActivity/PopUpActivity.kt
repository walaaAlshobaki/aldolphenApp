package com.adolphinpos.adolphinpos.pupaupActivity

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.*
import com.vdx.designertoast.DesignerToast
import kotlinx.android.synthetic.main.activity_pop_up.*
import kotlinx.android.synthetic.main.activity_pos_setting.*


class PopUpActivity : AppCompatActivity(), AddHallsDelegate, AddTableDelegate {

    var mPresenter: AddHallsPresenter? = null
    var tPresenter: AddTablePresenter? = null
    var name:Int=0
    var status:Int=0

    val DAYS_OPTIONS = arrayOf<CharSequence>(

        "Free",
        "Reserved",
        "Checked In",
        "Ordered",
        "Blocked",
        "Delivered",

        )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pop_up)

        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        mPresenter = AddHallsPresenter(this)
        mPresenter!!.delegate = this
        tPresenter = AddTablePresenter(this)
        tPresenter!!.delegate = this

        val bundle = intent.extras

        if (bundle != null) {

            if (bundle.getString("action") == "halls") {
                addTable.visibility = View.GONE
                addHalls.visibility = View.VISIBLE
            }else if (bundle.getString("action")=="table") {
                name = bundle.getInt("hallId")
                addTable.visibility = View.VISIBLE
                addHalls.visibility = View.GONE
            }
        }
//        email=bundle!!.getString("email").toString()

        val adapter: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, DAYS_OPTIONS)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        Tablestatus.setAdapter(adapter)



        Tablestatus.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setTextColor(resources.getColor(R.color.red  )) //Change selected text color

                status=position
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

        confirmTable.setOnClickListener {
            if (bundle != null) {
                if (bundle.getString("action") == "table") {
                    name = bundle.getInt("hallId")
                    addTable.visibility = View.VISIBLE


                    if (!!TableName.text.toString().isNullOrEmpty() || !!TableNum.text.toString()
                            .isNullOrEmpty()
                        || !!TableChairs.text.toString().isNullOrEmpty()
                    ) {
                        DesignerToast.Custom(
                            this,
                            "All filed is required",
                            Gravity.TOP or Gravity.RIGHT,
                            Toast.LENGTH_LONG,
                            R.drawable.warnings_background,
                            16,
                            "#FFFFFF",
                            R.drawable.ic_warninges,
                            55,
                            219
                        );

                    } else {

//                        id:Int,label: String,status:String,numberOfChairs:Int ,hallId:Int
                        tPresenter!!.AddTableTap(
                            TableNum.text.toString().toInt(),
                            TableName.text.toString(),
                            (Tablestatus.selectedItemPosition +1).toString(),
                            TableChairs.text .toString().toInt(),
                            name
                        )
                    }
                }
            }
        }
        confirmHall.setOnClickListener {
            if (bundle != null) {

                if (bundle.getString("action")=="halls") {
                    addTable.visibility=View.VISIBLE
                    if (!!hallsName.text.toString().isNullOrEmpty()){
                        DesignerToast.Custom(
                            this,
                            "All filed is required",
                            Gravity.TOP or Gravity.RIGHT,
                            Toast.LENGTH_LONG,
                            R.drawable.warnings_background,
                            16,
                            "#FFFFFF",
                            R.drawable.ic_warninges,
                            55,
                            219
                        );

                    }else{
                        mPresenter!!.AddHallTap(hallsName.text.toString(), common.branchId)
                    }

                }
            }

        }
    }

    override fun didAddHallsSuccess(token: AddHallsModel) {

        finish()
    }

    override fun didAddHallsFail(msg: String) {
        if (msg=="Added"){
            finish()
        }else
        DesignerToast.Custom(
            this, msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.erroe_background, 16, "#FFFFFF", R.drawable.ic_cancel1, 55, 219
        )
    }

    override fun didAddTableSuccess(token: AddHallsModel) {
        finish()
    }

    override fun didAddTableFail(msg: String) {
        if (msg=="Updated"){
            finish()
        }else
            DesignerToast.Custom(
                this, msg, Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.erroe_background, 16, "#FFFFFF", R.drawable.ic_cancel1, 55, 219
            )
    }
}