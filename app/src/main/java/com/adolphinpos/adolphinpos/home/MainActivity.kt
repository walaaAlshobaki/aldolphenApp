package com.adolphinpos.adolphinpos.home

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.createPOS.PosCatCompanyDataActivity
import com.adolphinpos.adolphinpos.createPOS.PosCatagoryActivity
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
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
import kotlinx.android.synthetic.main.activity_main.user
import kotlinx.android.synthetic.main.activity_main.userImage
import kotlinx.android.synthetic.main.alert.view.*

class MainActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate,ServicesDelegate{
    private lateinit var dashboardAdapter: DashboardAdapter
//   var dashboardModel: ArrayList<HomeModel> = ArrayList()
    var dashboardModel: ArrayList<ServiceTypeModel.Data> = ArrayList()
    var mPresenter: ServicesPresenter? = null
    val MY_PERMISSIONS_REQUEST_LOCATION = 99
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        if (userInfo.profilePicturePath==""){
            Log.d("profilePicturePath", userInfo.profilePicturePath.toString())
            Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        }else{


            val cleanImage: String =
                    userInfo.profilePicturePath!!.replace("data:image/png;base64,", "").replace(
                            "data:image/jpeg;base64,",
                            ""
                    )

            val decodedString: ByteArray = Base64.decode(cleanImage, Base64.DEFAULT)
            val decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)

//        Picasso.get().load(decodedByte).error(R.drawable.user).placeholder(R.drawable.user)
//        .into(avatar_img)

            common.loadBitmapByPicasso(this, decodedByte, userImage)

        }
        mPresenter = ServicesPresenter(this)
        mPresenter!!.delegate = this

        val llm = GridLayoutManager(this, 6)
        llm.orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = llm
        checkLocationPermission()
//        setDashbordData()
        dashboardAdapter = DashboardAdapter(this, dashboardModel,"DashboardViewHolder")
        mPresenter!!.getService()
        dashboardAdapter.setOnClickItemCategory(this)
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.contryId!!.toString())
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
fun checkLocationPermission(): Boolean {
        return if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            } else {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_LOCATION
                )
            }
            false
        } else {
            true
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        when (requestCode) {
            MY_PERMISSIONS_REQUEST_LOCATION -> {
                if (grantResults.size > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED
                ) {
                    if (ContextCompat.checkSelfPermission(
                            this,
                            Manifest.permission.ACCESS_FINE_LOCATION
                        )
                        == PackageManager.PERMISSION_GRANTED
                    ) {

                    }
                } else {
                    Toast.makeText(
                        this, "permission denied",
                        Toast.LENGTH_LONG
                    ).show()
                }
                return
            }
        }

}
    override fun onSelectItemCategory(position: Int) {
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.token)
        Log.d("QQQQQQQQQQQQQQQQQQ", userInfo.companyId.toString())
        Log.d("QQQQQQQQQQQQQQQQQQ", dashboardModel[position].id.toString())

            val i = Intent(this, PosCatagoryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            i.putExtra("serviceTypeId",dashboardModel[position].id)
            startActivity(i)


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