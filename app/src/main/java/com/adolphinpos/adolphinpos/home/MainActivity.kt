package com.adolphinpos.adolphinpos.home

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.helper.Alert
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.login.userInfo.UserOptionsActivity
import com.adolphinpos.adolphinpos.productManagerHomePage.ProductManagerMainActivity
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.lock.LockActivity
import com.adolphinpos.adolphinpos.userProfile.UserProfileActivity
import com.squareup.picasso.Picasso
import com.tapadoo.alerter.Alerter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert.view.*

class MainActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate,ServicesDelegate{
    private lateinit var dashboardAdapter: DashboardAdapter
//   var dashboardModel: ArrayList<HomeModel> = ArrayList()
    var dashboardModel: ArrayList<ServiceTypeModel.Data> = ArrayList()
    var mPresenter: ServicesPresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        mPresenter = ServicesPresenter(this)
        mPresenter!!.delegate = this

        val llm = GridLayoutManager(this, 6)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
//        setDashbordData()
        dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")
        mPresenter!!.getService()
        dashboardAdapter.setOnClickItemCategory(this)

//        recyclerView.measure(
//            View.MeasureSpec.makeMeasureSpec(
//                recyclerView.width,
//                View.MeasureSpec.EXACTLY
//            ), View.MeasureSpec.UNSPECIFIED
//        )
        user.setOnClickListener {
            val intent = Intent(applicationContext, UserOptionsActivity::class.java)
            startActivity(intent)
        }


        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 7) {
                doLogout()
            }
        }


        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 8) {
                val intent = Intent(applicationContext, UserProfileActivity::class.java)
                startActivity(intent)

            }
        }
    }
    fun setDashbordData() {

        val data1 = HomeModel("POS",2, R.drawable.pos,"POS")
        val data2 = HomeModel("settings",0, R.drawable.setting,"setting")
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
//        dashboardModel.add(data2)
        Log.d("didGetServicesSuccess",dashboardModel.toString())
        dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")

        dashboardAdapter.notifyDataSetChanged()

    }

    override fun onSelectItemCategory(position: Int) {
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.token)
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.companyId)
        if (dashboardModel[position].name=="Restaurant"){
            val i = Intent(this, ProductManagerMainActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else{
            val i = Intent(this, LockActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }

    }

    override fun onSelectItemProduct(position: Int, action: String) {
        TODO("Not yet implemented")
    }

    override fun didGetServicesSuccess(response: ServiceTypeModel) {
        try {
            Log.d("EEEEEEEEEEEEEEEEE",response.toString())
        dashboardModel.addAll(response.data)
//            setDashbordData()
//
//        val data2 = ServiesModel.Data(0,"settings",null, R.drawable.setting,"setting")
//        dashboardModel.add(data2)
            dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")
            dashboardAdapter.setOnClickItemCategory(this)
            dashboardAdapter.notifyDataSetChanged()
            recyclerView.adapter = dashboardAdapter
//            Log.d("didGetServicesSuccess",dashboardModel.toString())
//            dashboardAdapter.notifyDataSetChanged()
    } catch (ex: Exception) {

        Log.d("apiExepction inside", ex.localizedMessage)


    }
    }
    private fun emptyCell() {

        dashboardModel.clear()
        runOnUiThread {
            dashboardAdapter.notifyDataSetChanged()
//            animation_view.visibility = View.VISIBLE
//            progress_bar.visibility = View.GONE
//            recyclerView.visibility = View.GONE
        }

    }

    override fun didGetServicesFail(msg: String) {
//        runOnUiThread {
//            emptyCell()
//        }
    }

    override fun didEmpty() {
        runOnUiThread {
            emptyCell()
        }
    }


    fun doLogout() {


        Alerter.create(this, R.layout.alert)
            .setDuration(1000000)

            .setBackgroundColorRes(R.color.appColor)
            .also { alerter ->

                val container = alerter.getLayoutContainer()!!.rootView
                container.tvCustomLayout.text = resources.getString(R.string.logoutmsg)

                container.ok.setOnClickListener {
                    Alert.Instance.showMessage(this as Activity,R.string.logout,true)
                    Alerter.hide()
                    common.session!!.logoutUser()



                    val i = this!!.packageManager
                        .getLaunchIntentForPackage(this!!.packageName)
                    i!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(i)
                   finish()

                }

                container.no.setOnClickListener {
                    Alerter.hide()

                }
            }
            .show()






    }
}