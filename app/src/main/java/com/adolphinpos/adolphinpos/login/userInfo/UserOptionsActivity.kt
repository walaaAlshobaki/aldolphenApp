package com.adolphinpos.adolphinpos.login.userInfo

import android.content.pm.ActivityInfo
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.home.HomeModel
import kotlinx.android.synthetic.main.activity_main.*

class UserOptionsActivity : AppCompatActivity()  , DashboardAdapter.OnItemselectedDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<HomeModel> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_options)
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }

        val llm = GridLayoutManager(this, 2)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        setDashbordData()

//        mPresenter!!.getService()
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
    }

    fun setDashbordData() {

        val data1 = HomeModel("Profile",2, R.drawable.ic_profile,"Profile")
        val data2 = HomeModel("Logout",0, R.drawable.ic_logoutn,"Logout")
//        val data3 = HomeModel(" ")
//
//
//
//        dashboardModel.add(data1)
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
//
//
//        dashboardAdapter = DashboardAdapter(this, dashboardModel)



        dashboardModel.add(data1)
        dashboardModel.add(data2)
        Log.d("didGetServicesSuccess",dashboardModel.toString())
        dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")
        dashboardAdapter.notifyDataSetChanged()

    }


    override fun onSelectItemCategory(position: Int) {
        if (dashboardModel[position].name=="Profile"){
            RxBus.publish(MessageEvent(8, dashboardModel[position]))
            finish()
        }else if (dashboardModel[position].name=="Logout"){
            RxBus.publish(MessageEvent(7, dashboardModel[position]))
            finish()
            }

    }

    override fun onSelectItemProduct(position: Int, action: String) {
        TODO("Not yet implemented")
    }
}