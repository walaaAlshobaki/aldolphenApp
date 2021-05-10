package com.adolphinpos.adolphinpos.employee_permissions

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addEmp.PoliicyDelegate
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import com.adolphinpos.adolphinpos.addEmp.PoliicyPresenter
import com.adolphinpos.adolphinpos.authorized_employees.AuthorizedEmployeesActivity
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.policyManagement.AddPolicyModel
import com.adolphinpos.adolphinpos.policyManagement.PolicyManagementActivity
import com.adolphinpos.adolphinpos.steps.Step2Activity
import com.squareup.picasso.Picasso
import com.vdx.designertoast.DesignerToast
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
    var policeName: String?=""
    var action: String?="old"
    var PoliicyPermissionPresenter: PoliicyPermissionPresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emp_permissions)


        val bundle = intent.extras
        if (bundle!=null){
            if (!bundle.getString("name").isNullOrEmpty() ){
                usere.text=bundle.getString("name")
                emp_email.text=bundle.getString("email")
            }
        }
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 20) {
                countryModel = it.message as UserEmployeeModel.Data
                usere.text=countryModel!!.firstName+" "+countryModel!!.lastName
                emp_email.text=countryModel!!.email
            }
        }


        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 0) {

                policeName=it.message.toString()
                if (common.prermtion[0]==0){
                    DesignerToast.Custom(this,"PLZ select the permissions", Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                        R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                }else{
                    common.prermtion.remove(0)
                    mPresenter!!.addPoliicy(policeName!!, common.prermtion)

                }

            }
        }

        mPresenter=PoliicyPresenter(this)
        mPresenter!!.delegate = this
        PoliicyPermissionPresenter=PoliicyPermissionPresenter(this)
        PoliicyPermissionPresenter!!.delegate = this

        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName


        sign.setOnClickListener {
            val i = Intent(this, Step2Activity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }



        confirm.setOnClickListener {

            if (action=="new"){
                if (common.prermtion[0]==0){
                    DesignerToast.Custom(this,"PLZ select the permissions", Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                        R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
                }else{
                    val i = Intent(this, PolicyManagementActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    startActivity(i)
                }

            }
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
        mModelList.clear()
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

    override fun didAddPoliicySuccess(response: AddPolicyModel) {
        getListData()
        DesignerToast.Custom(this,"Added successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
            R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)

    }

    override fun didAddPoliicyFail(msg: String) {
        Log.d("WWWWWWWWWWWWWWWWWWWW",msg)
        if (msg=="true"|| msg=="null"){
            getListData()
            DesignerToast.Custom(this,"Added successfully ", Gravity.TOP or Gravity.RIGHT, Toast.LENGTH_LONG,
                R.drawable.sacssful_background,16,"#FFFFFF",R.drawable.ic_checked, 55, 219)
        }else{

            DesignerToast.Custom(this,msg, Gravity.TOP or Gravity.RIGHT,Toast.LENGTH_LONG,
                R.drawable.erroe_background,16,"#FFFFFF",R.drawable.ic_cancel1, 55, 219)
        }


    }

    override fun onSelectItemCategory(position: Int) {
        if (mModelList[position].id==-2){
            action="new"
            PoliicyPermissionPresenter!!.getPermission()

        }else{
            action="old"
            PoliicyPermissionPresenter!!.getPoliicyPermission(mModelList[position].id.toString())

        }


        for (n in mModelList.indices){
            mModelList[n].isSelected = n==position
        }
        mAdapter = DashboardAdapter(this, mModelList,"EmpPermissionsActivity")
        mAdapter!!.setOnClickItemCategory(this)
        recyclerView.adapter = mAdapter
        mAdapter!!.notifyDataSetChanged()




    }

    override fun onSelectItemProduct(position: Int, action: String) {
        TODO("Not yet implemented")
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