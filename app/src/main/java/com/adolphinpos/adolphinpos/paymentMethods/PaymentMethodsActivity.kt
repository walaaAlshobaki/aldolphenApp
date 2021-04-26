package com.adolphinpos.adolphinpos.paymentMethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeDelegate
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeModel
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypePresenter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.activity_currency.*
import kotlinx.android.synthetic.main.activity_payment_methods.*
import kotlinx.android.synthetic.main.activity_payment_methods.recyclerView

class PaymentMethodsActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate,
    CurrencyTypeDelegate {
    var currencyModel: ArrayList<CurrencyTypeModel.Data> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    var mPresenter: CurrencyTypePresenter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_methods)
        val llm = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val llm2 = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = llm
        recyclerViewCurrency.layoutManager = llm2
        mPresenter = CurrencyTypePresenter(this)
        mPresenter!!.delegate = this

        mPresenter!!.getCurrencyType()
        dashboardAdapter= DashboardAdapter(this, currencyModel,"PaymentMethodsActivity")
        dashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
        recyclerViewCurrency.adapter = dashboardAdapter
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetCurrencyTypeSuccess(response: CurrencyTypeModel) {
        try {
            currencyModel.addAll(response.data)
            dashboardAdapter = DashboardAdapter(this, currencyModel,"PaymentMethodsActivity")
            dashboardAdapter.setOnClickItemCategory(this)
            dashboardAdapter.notifyDataSetChanged()
            recyclerView.adapter = dashboardAdapter
            recyclerViewCurrency.adapter = dashboardAdapter
        } catch (ex: Exception) {

            Log.d("apiExepction inside", ex.localizedMessage)


        }
    }

    override fun didGetCurrencyTypeFail(msg: String) {

    }

    override fun didEmpty() {

    }
}