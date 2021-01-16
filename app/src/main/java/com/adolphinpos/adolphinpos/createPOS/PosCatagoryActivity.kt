package com.adolphinpos.adolphinpos.createPOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.home.HomeModel
import kotlinx.android.synthetic.main.activity_pos_catagory.*

class PosCatagoryActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_catagory)
        val llm = GridLayoutManager(this, 4)
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


        val data1 = HomeModel("Restaurant", R.drawable.restaurant,"restaurant")
        val data2 = HomeModel("Supermarket", R.drawable.supermarket,"supermarket")
        val data3 = HomeModel("Caffee", R.drawable.caffee,"caffee")
        val data4 = HomeModel("Smart assistant", R.drawable.smart,"smart assistant")
        val data5 = HomeModel("Hotel", R.drawable.hotel,"Hotel")
        val data6 = HomeModel("Hypermarket", R.drawable.hypermarket,"hypermarket")
        val data7 = HomeModel("Restaurant & caffee", R.drawable.restaurantcaffee,"Restaurant & caffee")




        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)
        dashboardModel.add(data5)
        dashboardModel.add(data6)
        dashboardModel.add(data7)



        dashboardAdapter = DashboardAdapter(this, dashboardModel)

    }

    override fun onSelectItemCategory(position: Int) {
        val i = Intent(this, PosCatCompanyDataActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }
}