package com.adolphinpos.adolphinpos.createPOS

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.home.HomeModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class CreatePosActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate{
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_pos)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        val llm = GridLayoutManager(this, 6)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        setDashbordData()
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
    }
    fun setDashbordData() {


        val data1 = HomeModel("create POS", R.drawable.ic_add,"createPOS")
        val data2 = HomeModel("settings", R.drawable.setting,"settings")
        val data3 = HomeModel(" ")



        dashboardModel.add(data1)
//        dashboardModel.add(data2)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)
//        dashboardModel.add(data3)


        dashboardAdapter = DashboardAdapter(this, dashboardModel)

    }

    override fun onSelectItemCategory(position: Int) {

        val i = Intent(this, PosCatagoryActivity::class.java)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)
    }
}