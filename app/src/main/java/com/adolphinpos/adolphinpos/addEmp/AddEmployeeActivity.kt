package com.adolphinpos.adolphinpos.addEmp

import android.graphics.ColorSpace.Model
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.RecyclerViewAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.registeration.country.CountryPresenter
import kotlinx.android.synthetic.main.activity_add_employee.*


class AddEmployeeActivity : AppCompatActivity() ,PoliicyDelegate,UserInviteDelegate{

    var mModelList: ArrayList<PoliicyModel.Data> = ArrayList()
    private var mAdapter: RecyclerView.Adapter<*>? = null

    var mPresenter: PoliicyPresenter?=null
    var IPresenter: UserInvitePresenter?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_employee)
        mPresenter=PoliicyPresenter(this)
        mPresenter!!.delegate = this
        IPresenter=UserInvitePresenter(this)
        IPresenter!!.delegate = this
        Log.d("selectedServiceId", common.selectedServiceId.toString())
        Log.d("selectedServiceTypeId", common.selectedServiceTypeId.toString())
        val bundle = intent.extras
        if (bundle!=null){
            if (!bundle!!.getString("action").isNullOrEmpty() && bundle!!.getString("action")=="new"){
                BranchTextInputLayout.visibility=View.GONE
            }
        }

        mAdapter = RecyclerViewAdapter(mModelList,this)
        val linearVertical = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
       recyclerView!!.layoutManager = linearVertical

        recyclerView.setHasFixedSize(true)
        recyclerView.setAdapter(mAdapter)
        getListData()


        loginBtn.setOnClickListener {
            IPresenter!!.userInvite(Email.text.toString(),UserName.text.toString(),PhoneNo.text.toString(),
                common.mutableList as ArrayList<Int>
            )
        }
    }

     fun getListData(){
         mPresenter!!.getPoliicy()


    }

    override fun didGetPoliicySuccess(response: PoliicyModel) {

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

    override fun didUserInviteSuccess(token: AddModel) {

    }

    override fun didUserInviteFail(msg: String) {

    }
}