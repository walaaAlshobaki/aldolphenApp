package com.adolphinpos.adolphinpos.createPOS

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.home.HomeModel
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethodsActivity
import com.adolphinpos.adolphinpos.productManagerHomePage.ProductManagerMainActivity
import kotlinx.android.synthetic.main.activity_pos_catagory.*

class PosCatagoryActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pos_catagory)
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }
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


        val data1 = HomeModel("Burger Restaurant", 1,R.drawable.ic_burger,"Burger Restaurant")

        val data3 = HomeModel("Shawarma",2, R.drawable.ic_popularmeals,"Shawarma")
        val data4 = HomeModel("Popular Meals",3, R.drawable.ic_popularmeals,"Popular Meals")
        val data5 = HomeModel("Other",4, R.drawable.ic_othermeals,"Other")
//        val data6 = HomeModel("Hypermarket",6, R.drawable.hypermarket,"hypermarket")
//        val data7 = HomeModel("Restaurant & caffee",7, R.drawable.restaurantcaffee,"Restaurant & caffee")




        dashboardModel.add(data1)
//        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)
        dashboardModel.add(data5)
//        dashboardModel.add(data6)
//        dashboardModel.add(data7)



        dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")

    }

    override fun onSelectItemCategory(position: Int) {
        common.selectedServiceTypeId=dashboardModel[position].id
//        val i = Intent(this, ProductManagerMainActivity::class.java)
        val i = Intent(this, PaymentMethodsActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
        finish()
    }

    override fun onSelectItemProduct(position: Int, action: String) {
        TODO("Not yet implemented")
    }
}