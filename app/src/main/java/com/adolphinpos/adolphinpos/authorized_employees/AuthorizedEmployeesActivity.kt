package com.adolphinpos.adolphinpos.authorized_employees

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.Adapters.MainAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addEmp.AddEmployeeActivity
import com.adolphinpos.adolphinpos.addEmp.PoliicyModel
import com.adolphinpos.adolphinpos.createPOS.PosSettingActivity
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.login.resetPassword.ResetPasswordPresenter
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_authorized_employees.*
import kotlinx.android.synthetic.main.activity_authorized_employees.recyclerView
import kotlinx.android.synthetic.main.activity_country.*

class AuthorizedEmployeesActivity : AppCompatActivity() ,UsersDelegate,MainAdapter.OnItemselectedDelegate{
    var mPresenter: UsersPresenter? = null
    var mModelList: ArrayList<UserEmployeeModel.Data> = ArrayList()
    private var mAdapter: MainAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authorized_employees)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text= userInfo.firstName +" "+ userInfo.lastName
        mPresenter = UsersPresenter(this)
        mPresenter!!.delegate = this
        mPresenter!!.getUsersTap(userInfo.userId.toString())
        val llm = GridLayoutManager(this, 2)
        recyclerView.layoutManager = llm
        getListData()
        recyclerView.adapter = mAdapter


        add.setOnClickListener {
            val i = Intent(this, AddEmployeeActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        sign.setOnClickListener {
            val i = Intent(this, PosSettingActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
            finish()
        }



    }
    fun getListData(){
        mAdapter = MainAdapter(context = this,mModelList,"AuthorizedEmployeesViewHolder")
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

    override fun onSelectItemCategory(position: Int) {

    }
}