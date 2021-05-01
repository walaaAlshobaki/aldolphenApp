package com.adolphinpos.adolphinpos.paymentMethods

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.CurrencyTypeAdapter
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.Adapters.PaymentAdapter
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.AddCurrencyTypeModel
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeDelegate
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypeModel
import com.adolphinpos.adolphinpos.CurrencyTypeActivity.CurrencyTypePresenter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import kotlinx.android.synthetic.main.activity_payment_methods.*
import kotlinx.android.synthetic.main.activity_payment_methods.recyclerView

class PaymentMethodsActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate,
    CurrencyTypeDelegate ,PaymentMethoodDelegate{
    var currencyModel: ArrayList<CurrencyTypeModel.Data> = ArrayList()
    var paymentMethoodModel: ArrayList<PaymentMethoodModel.Data> = ArrayList()
    private lateinit var dashboardAdapter: CurrencyTypeAdapter
    private lateinit var paymentMethoodDashboardAdapter: PaymentAdapter
    var mPresenter: CurrencyTypePresenter? = null
    var paymentMethoodPresenter: PaymentMethoodPresnter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment_methods)
        val llm = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        val llm2 = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
        recyclerView.layoutManager = llm
        recyclerViewCurrency.layoutManager = llm2
        mPresenter = CurrencyTypePresenter(this)
        mPresenter!!.delegate = this
        paymentMethoodPresenter = PaymentMethoodPresnter(this)
        paymentMethoodPresenter!!.delegate = this

        mPresenter!!.getCurrencyType()
        paymentMethoodPresenter!!.getPaymentMethood()
        dashboardAdapter= CurrencyTypeAdapter( currencyModel,this)
//        dashboardAdapter.setOnClickItemCategory(this)
        paymentMethoodDashboardAdapter= PaymentAdapter(paymentMethoodModel,this)
//        paymentMethoodDashboardAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = dashboardAdapter
        recyclerViewCurrency.adapter = paymentMethoodDashboardAdapter
        confirm.setOnClickListener {
            mPresenter!!.addCurrencyType(common.userCurrencyType)
            paymentMethoodPresenter!!.addPaymentMethood(common.userPayment)
        }
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetCurrencyTypeSuccess(response: CurrencyTypeModel) {
        try {
            currencyModel.addAll(response.data)
            dashboardAdapter = CurrencyTypeAdapter( currencyModel,this)

            dashboardAdapter.notifyDataSetChanged()
            recyclerView.adapter = dashboardAdapter

        } catch (ex: Exception) {

            Log.d("apiExepction inside", ex.localizedMessage)


        }
    }

    override fun didGetCurrencyTypeFail(msg: String) {

    }

    override fun didGetPaymentMethoodSuccess(response: PaymentMethoodModel) {
     paymentMethoodModel.addAll(response.data)
        paymentMethoodDashboardAdapter = PaymentAdapter(paymentMethoodModel,this)

        paymentMethoodDashboardAdapter.notifyDataSetChanged()
        recyclerViewCurrency.adapter = paymentMethoodDashboardAdapter

    }

    override fun didGetPaymentMethoodFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didAddPaymentMethoodSuccess(response: AddCurrencyTypeModel) {

    }

    override fun didAddPaymentMethoodFail(msg: String) {

    }

    override fun didAddCurrencyTypeSuccess(response: AddCurrencyTypeModel) {

    }



    override fun didAddCurrencyTypeFail(msg: String) {

    }
}