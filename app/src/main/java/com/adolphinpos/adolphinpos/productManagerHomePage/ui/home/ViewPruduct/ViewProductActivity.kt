package com.adolphinpos.adolphinpos.productManagerHomePage.ui.home.ViewPruduct

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel

class ViewProductActivity : AppCompatActivity() {
    var mModelList: ArrayList<AdditionalModel> = ArrayList()
    private var mAdapter: RecyclerView.Adapter<*>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_product)

    }
}