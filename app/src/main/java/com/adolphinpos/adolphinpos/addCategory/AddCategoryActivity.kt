package com.adolphinpos.adolphinpos.addCategory

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_add_category.*

class AddCategoryActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate {
    var categoryModel: ArrayList<IconModel> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)
        categoryModel.add(IconModel(-2,"",true))
        categoryModel.add(IconModel(1,"",false))
        categoryModel.add(IconModel(2,"",false))
        categoryModel.add(IconModel(3,"",false))
        categoryModel.add(IconModel(4,"",false))
        categoryModel.add(IconModel(5,"",false))
        categoryModel.add(IconModel(6,"",false))
        mAdapter = DashboardAdapter(this, categoryModel,"AddCategoryActivity")
        mAdapter.notifyDataSetChanged()

        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerViewIcon!!.layoutManager = linearVertical
        recyclerViewIcon.setHasFixedSize(true)
        recyclerViewIcon.setAdapter(mAdapter)
    }

    override fun onSelectItemCategory(position: Int) {
        for (n in categoryModel.indices){
            categoryModel[n].isSelected = n==position
        }
        mAdapter = DashboardAdapter(this, categoryModel,"AddCategoryActivity")
        mAdapter!!.setOnClickItemCategory(this)
        recyclerViewIcon.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()
    }

    override fun onSelectItemProduct(position: Int, action: String) {
        TODO("Not yet implemented")
    }
}