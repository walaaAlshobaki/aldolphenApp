package com.adolphinpos.adolphinpos.steps

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.adolphinpos.adolphinpos.Adapters.MainAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addEmp.AddEmployeeActivity
import com.adolphinpos.adolphinpos.authorized_employees.UserEmployeeModel
import com.adolphinpos.adolphinpos.authorized_employees.UsersDelegate
import com.adolphinpos.adolphinpos.authorized_employees.UsersPresenter
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
import com.adolphinpos.adolphinpos.employee_permissions.EmpPermissionsActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_step2.*

class Step2Activity :  AppCompatActivity() , UsersDelegate,MainAdapter.OnItemselectedDelegate{
    var mPresenter: UsersPresenter? = null
    var mModelList: ArrayList<UserEmployeeModel.Data> = ArrayList()
    private var mAdapter: MainAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_step2)
        Picasso.get().load(R.drawable.user).transform(
            CircleTransform()
        ).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        mPresenter = UsersPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUsersTap(userInfo.companyId.toString())
        val llm = GridLayoutManager(this, 2)
        recyclerView.layoutManager = llm
        getListData()

        recyclerView.adapter = mAdapter


        add.setOnClickListener {
            val i = Intent(this, AddEmployeeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        delete.setOnClickListener {
            Log.d("**********************",common.usersDelete.toString())
            mModelList!!.remove(common.usersDelete)
            mAdapter!!.notifyDataSetChanged()
//            mPresenter!!.getDeleteUsersTap(common.usersDelete)

        }
        next.setOnClickListener {
            val i = Intent(this, Step3Activity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }



    }
    fun getListData(){
        mAdapter = MainAdapter(context = this,mModelList,"AuthorizedEmployeesViewHolder")
        mAdapter!!.setOnClickItemCategory(this)
        mAdapter!!.notifyDataSetChanged()
        Log.d("didGetUsersSuccess",mModelList.toString())
        recyclerView.adapter = mAdapter


    }


    private fun emptyCell() {

        mModelList.clear()
        runOnUiThread {
            mAdapter!!.notifyDataSetChanged()

        }

    }
    override fun didGetUsersSuccess(token: UserEmployeeModel) {

        try {
//    mModelList.addAll(token.data)
            mModelList.clear()
            mModelList.addAll(token.data)
            getListData()
            mAdapter!!.notifyDataSetChanged()

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

    override fun didDeleteUsersSuccess(token: UserEmployeeModel) {

    }

    override fun didGetDeleteUsersFail(msg: String) {

    }

    override fun onSelectItemCategory(position: Int) {
        RxBus.publish(MessageEvent(20, mModelList[position]))
        val i = Intent(this, EmpPermissionsActivity::class.java)
        i.putExtra("name",mModelList[position].firstName+" "+mModelList[position].lastName)
        i.putExtra("email",mModelList[position].email)
        i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(i)

    }
}