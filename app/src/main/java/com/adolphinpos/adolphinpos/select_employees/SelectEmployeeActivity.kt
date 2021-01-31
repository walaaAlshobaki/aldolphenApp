package com.adolphinpos.adolphinpos.select_employees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.Adapters.MainAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addEmp.AddEmployeeActivity
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.authorized_employees.UsersDelegate
import com.adolphinpos.adolphinpos.authorized_employees.UsersPresenter
import com.adolphinpos.adolphinpos.createPOS.CreatePosActivity
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.home.HomeModel
import kotlinx.android.synthetic.main.activity_authorized_employees.*
import kotlinx.android.synthetic.main.activity_select_employee.*
import kotlinx.android.synthetic.main.activity_select_employee.recyclerView

class SelectEmployeeActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate,
    UsersDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    var dashboardModel: ArrayList<UserEmployeeModel.Data> = ArrayList()
    var mPresenter: UsersPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_employee)
        mPresenter = UsersPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUsersTap(userInfo.userId.toString())
        val llm = GridLayoutManager(this, 3)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        getListData()

//        mPresenter!!.getService()
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
    }
    fun getListData(){
        dashboardAdapter = DashboardAdapter(context = this,dashboardModel)
        dashboardAdapter.setOnClickItemCategory(this)
        dashboardAdapter!!.notifyDataSetChanged()
        Log.d("didGetUsersSuccess",dashboardModel.toString())
        recyclerView.adapter = dashboardAdapter


    }
    private fun emptyCell() {

        dashboardModel.clear()
        runOnUiThread {
            dashboardAdapter!!.notifyDataSetChanged()

        }

    }
    fun setDashbordData() {

        val data1 = HomeModel("NEW USER",2, R.drawable.ic_add,"new")

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



//        dashboardModel.add(data1)
////        dashboardModel.add(data2)
//        Log.d("didGetServicesSuccess",dashboardModel.toString())
//        dashboardAdapter = DashboardAdapter(this, dashboardModel)
//        dashboardAdapter.notifyDataSetChanged()

    }
    override fun onSelectItemCategory(position: Int) {
        if (dashboardModel[position].firstName=="NEW") {
            val i = Intent(this, AddEmployeeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("action","new")
            startActivity(i)

        }else{
            RxBus.publish(MessageEvent(2, dashboardModel[position]))

            onBackPressed()

        }
    }

    override fun didGetUsersSuccess(token: UserEmployeeModel) {

        try {
//    mModelList.addAll(token.data)
            dashboardModel.clear()
            dashboardModel.addAll(token.data)
            val data1 = UserEmployeeModel.Data("NEW",0,"USER"," ",
                R.drawable.ic_add)
            dashboardModel.add(data1)
            getListData()

//    mAdapter!!.notifyDataSetChanged()
        }catch (ex: Exception) {

            Log.d("apiExepction inside", ex.localizedMessage)


        }






    }

    override fun didGetUsersFail(msg: String) {
//        runOnUiThread {
//            emptyCell()
//        }
    }

    override fun didEmpty() {
        runOnUiThread {
            emptyCell()
        }
    }
}