package com.adolphinpos.adolphinpos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_web.*

class WebActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web)
        webView!!.settings.databaseEnabled = true
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.javaScriptCanOpenWindowsAutomatically = true
        webView!!.settings.builtInZoomControls = false
        webView!!.settings.loadsImagesAutomatically = true
        webView.loadUrl("https://goldentime.co.com/")
    }
}