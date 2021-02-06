package com.adolphinpos.adolphinpos.employee_permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addEmp.PoliicyDelegate
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import com.adolphinpos.adolphinpos.addEmp.PoliicyPresenter
import com.adolphinpos.adolphinpos.authorized_employees.AuthorizedEmployeesActivity
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_authorized_employees.*
import kotlinx.android.synthetic.main.activity_emp_permissions.*
import kotlinx.android.synthetic.main.activity_emp_permissions.recyclerView
import kotlinx.android.synthetic.main.activity_emp_permissions.sign
import kotlinx.android.synthetic.main.activity_emp_permissions.userImage
import kotlinx.android.synthetic.main.activity_emp_permissions.userName


class EmpPermissionsActivity : AppCompatActivity(), PoliicyDelegate , DashboardAdapter.OnItemselectedDelegate,PoliicyPermissionDelegate {
    var mModelList: ArrayList<PoliicyModel.Data> = ArrayList()
    var PoliicyPermissionModelList: ArrayList<PoliicyPermissionModel.Data> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    private lateinit var PoliicyPermissionAdapter: DashboardAdapter

    var countryModel: UserEmployeeModel.Data? =null
    var mPresenter: PoliicyPresenter?=null
    var PoliicyPermissionPresenter: PoliicyPermissionPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_permissions)

        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 9) {
                countryModel = it.message as UserEmployeeModel.Data
                user.text=countryModel!!.firstName+" "+countryModel!!.lastName
                emp_email.text=countryModel!!.email
            }
        }
        mPresenter=PoliicyPresenter(this)
        mPresenter!!.delegate = this
        PoliicyPermissionPresenter=PoliicyPermissionPresenter(this)
        PoliicyPermissionPresenter!!.delegate = this

        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName


        sign.setOnClickListener {
            val i = Intent(this, AuthorizedEmployeesActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }

        mAdapter = DashboardAdapter(this, mModelList,"EmpPermissionsActivity")
        mAdapter.notifyDataSetChanged()

        mAdapter!!.setOnClickItemCategory(this)
        PoliicyPermissionAdapter = DashboardAdapter(this, PoliicyPermissionModelList,"PoliicyPermissionModel")
        PoliicyPermissionAdapter.notifyDataSetChanged()

        PoliicyPermissionAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView!!.layoutManager = linearVertical
        recyclerView.setHasFixedSize(true)
        recyclerView.setAdapter(mAdapter)
        getListData()
        val llm = GridLayoutManager(this, 2)
        PoliicyPermissionRec.layoutManager = llm
        PoliicyPermissionRec.setHasFixedSize(true)
        PoliicyPermissionRec.setAdapter(PoliicyPermissionAdapter)

    }

    fun getListData(){
        mPresenter!!.getPoliicy()


    }


    override fun didGetPoliicySuccess(response: PoliicyModel) {
        mModelList.add(PoliicyModel.Data(id = -2," + ",false))
        mModelList.addAll(response.data)
        Log.d("didGetPoliicySuccess",mModelList.toString())


        runOnUiThread {
            mAdapter!!.notifyDataSetChanged()

        }
    }

    override fun didGetPoliicyFail(msg: String) {

    }

    override fun didEmpty() {
    }

    override fun onSelectItemCategory(position: Int) {


        for (n in mModelList.indices){
            mModelList[n].isSelected = n==position
        }
        mAdapter = DashboardAdapter(this, mModelList,"EmpPermissionsActivity")
        mAdapter!!.setOnClickItemCategory(this)
        recyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()

        PoliicyPermissionPresenter!!.getPoliicyPermission(mModelList[position].id.toString())


    }

    override fun didGetPoliicyPermissionSuccess(response: PoliicyPermissionModel) {
        PoliicyPermissionModelList.clear()
        PoliicyPermissionAdapter!!.notifyDataSetChanged()
        PoliicyPermissionModelList.addAll(response.data)
        Log.d("didGetPoliicySuccess",PoliicyPermissionModelList.toString())


        runOnUiThread {
            PoliicyPermissionAdapter!!.notifyDataSetChanged()

        }
    }

    override fun didGetPoliicyPermissionFail(msg: String) {

    }

    override fun didEmptyPoliicyPermission() {

    }
}