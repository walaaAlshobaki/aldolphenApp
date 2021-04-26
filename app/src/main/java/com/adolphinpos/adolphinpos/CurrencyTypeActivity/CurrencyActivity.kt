package com.adolphinpos.adolphinpos.CurrencyTypeActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_currency.recyclerView

class CurrencyActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate,CurrencyTypeDelegate  {
    var currencyModel: ArrayList<CurrencyTypeModel.Data> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    var mPresenter: CurrencyTypePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency)
        val llm = GridLayoutManager(this, 4)
        recyclerView.layoutManager = llm
        mPresenter = CurrencyTypePresenter(this)
        mPresenter!!.delegate = this

        mPresenter!!.getCurrencyType()
        dashboardAdapter= DashboardAdapter(this, currencyModel,"currencyModel")
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
    }

    override fun onSelectItemCategory(position: Int) {


    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }
//    fun setDashbordData() {
//
//        val data1 = CurrencyTypeModel(1,"test1")
//        val data2 = CurrencyTypeModel(2,"test2")
//        val data3 = CurrencyTypeModel(3,"test3")
//        val data4 = CurrencyTypeModel(4,"test4")
//
////        val data3 = HomeModel(" ")
////
////
////
//        currencyModel.add(data1)
//        currencyModel.add(data2)
//        currencyModel.add(data3)
//        currencyModel.add(data4)
//        currencyModel.add(data1)
//        currencyModel.add(data2)
//        currencyModel.add(data3)
//        currencyModel.add(data4)
//        currencyModel.add(data1)
//        currencyModel.add(data2)
//        currencyModel.add(data3)
//        currencyModel.add(data4)
////
////
////
////        dashboardAdapter = DashboardAdapter(this, dashboardModel)
//
//
//
////        dashboardModel.add(data1)
////        dashboardModel.add(data2)
//        Log.d("didGetServicesSuccess",currencyModel.toString())
////        dashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"productManagmentModel")
//
////        dashboardAdapter.notifyDataSetChanged()
//
//    }

    override fun didGetCurrencyTypeSuccess(response: CurrencyTypeModel) {
        try {
            currencyModel.addAll(response.data)
            dashboardAdapter = DashboardAdapter(this, currencyModel,"currencyModel")
            dashboardAdapter.setOnClickItemCategory(this)
            dashboardAdapter.notifyDataSetChanged()
            recyclerView.adapter = dashboardAdapter
        } catch (ex: Exception) {

            Log.d("apiExepction inside", ex.localizedMessage)


        }
    }

    override fun didGetCurrencyTypeFail(msg: String) {

    }

    override fun didEmpty() {
    }
}