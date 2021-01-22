package com.adolphinpos.adolphinpos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.Splash.userConfig
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.createPOS.CreatePosActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.HomeModel
import com.adolphinpos.adolphinpos.registeration.register.RegisterActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate{
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName

        val llm = GridLayoutManager(this, 6)
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


        val data1 = HomeModel("POS", R.drawable.pos,"create")
        val data2 = HomeModel("settings", R.drawable.setting,"settings")
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
        dashboardModel.add(data3)
        dashboardModel.add(data3)


        dashboardAdapter = DashboardAdapter(this, dashboardModel)

    }

    override fun onSelectItemCategory(position: Int) {
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.token)
        if (dashboardModel[position].action=="create"){
            val i = Intent(this, CreatePosActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }

    }
}