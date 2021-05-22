package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_product_new_item.*

class ProductNewItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_new_item)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener {
            finish()
        }
    }
}