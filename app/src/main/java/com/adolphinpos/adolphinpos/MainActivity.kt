package com.adolphinpos.adolphinpos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.HomeModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate{
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        val llm = GridLayoutManager(this, 5)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        setDashbordData()
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
//        recyclerView.measure(
//            View.MeasureSpec.makeMeasureSpec(
//                recyclerView.width,
//                View.MeasureSpec.EXACTLY
//            ), View.MeasureSpec.UNSPECIFIED
//        )
    }
    fun setDashbordData() {


        val data1 = HomeModel("POS", R.drawable.pos)
        val data2 = HomeModel("settings", R.drawable.setting)
        val data3 = HomeModel(" ")



        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)
        dashboardModel.add(data3)


        dashboardAdapter = DashboardAdapter(this, dashboardModel)

    }

    override fun onSelectItemCategory(position: Int) {

    }
}