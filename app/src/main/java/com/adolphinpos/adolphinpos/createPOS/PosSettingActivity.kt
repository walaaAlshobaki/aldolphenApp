package com.adolphinpos.adolphinpos.createPOS

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.authorized_employees.AuthorizedEmployeesActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_pos_setting.*


class PosSettingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_setting)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        val country = arrayOf("Drinks Category", "USA", "China", "Japan", "Other")
        authEmps.setOnClickListener {
            val i = Intent(this, AuthorizedEmployeesActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }
        sign.setOnClickListener {
            val i = Intent(this, CreatePosActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }


        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(R.layout.spinner_dropdown_item)
        spinner.adapter = aa
        spinnerCategory.adapter = aa
        spinnerTax.adapter = aa
        spinnerBarcodes.adapter = aa
        spinner.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setTextColor(resources.getColor(R.color.red  )) //Change selected text color
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        spinnerCategory.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setTextColor(resources.getColor(R.color.red  )) //Change selected text color
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        spinnerTax.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setTextColor(resources.getColor(R.color.red  )) //Change selected text color
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })
        spinnerBarcodes.setOnItemSelectedListener(object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View,
                position: Int,
                id: Long
            ) {
                (view as TextView).setTextColor(resources.getColor(R.color.red  )) //Change selected text color
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        })

    }

}