package com.adolphinpos.adolphinpos.registeration.code

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.OtpEditText


class VerificationScreenCodeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verification_screen_code)
        val txtPinEntry: OtpEditText =
            findViewById<View>(R.id.txt_pin_entry) as OtpEditText
        txtPinEntry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable) {
                if (s.toString() == "123456") {
                    Toast.makeText(this@VerificationScreenCodeActivity, "Success", Toast.LENGTH_SHORT).show()
                } else if (s.length == "123456".length) {
                    Toast.makeText(this@VerificationScreenCodeActivity, "Incorrect", Toast.LENGTH_SHORT).show()
                    txtPinEntry.setText(null)
                }
            }
        })
    }
}