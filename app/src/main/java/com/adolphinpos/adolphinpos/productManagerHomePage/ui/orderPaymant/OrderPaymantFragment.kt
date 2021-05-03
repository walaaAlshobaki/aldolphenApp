package com.adolphinpos.adolphinpos.productManagerHomePage.ui.orderPaymant

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodDelegate
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodModel
import com.adolphinpos.adolphinpos.paymentMethods.PaymentMethoodPresnter
import kotlinx.android.synthetic.main.fragment_order_paymant.*

import kotlinx.android.synthetic.main.fragment_order_paymant.view.*


class OrderPaymantFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate,
        CurrencyTypeDelegate, PaymentMethoodDelegate {

    var currencyModel: ArrayList<CurrencyTypeModel.Data> = ArrayList()
    var paymentMethoodModel: ArrayList<PaymentMethoodModel.Data> = ArrayList()
    private lateinit var dashboardAdapter: CurrencyTypeAdapter

    private lateinit var paymentMethoodDashboardAdapter: PaymentAdapter
    var mPresenter: CurrencyTypePresenter? = null
    var paymentMethoodPresenter: PaymentMethoodPresnter? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        val llm = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
        val llm2 = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)

        view.recyclerView.layoutManager = llm
        view. recyclerViewCurrency.layoutManager = llm2
        mPresenter = CurrencyTypePresenter(requireActivity())
        mPresenter!!.delegate = this
        paymentMethoodPresenter = PaymentMethoodPresnter(requireActivity()!!)
        paymentMethoodPresenter!!.delegate = this

        mPresenter!!.getCurrencyType()
        paymentMethoodPresenter!!.getPaymentMethood()
        dashboardAdapter= CurrencyTypeAdapter( currencyModel,requireActivity()!!)
//        dashboardAdapter.setOnClickItemCategory(this)
        paymentMethoodDashboardAdapter= PaymentAdapter(paymentMethoodModel,requireActivity())
//        paymentMethoodDashboardAdapter.setOnClickItemCategory(this)
        view. recyclerView.adapter = dashboardAdapter
        view.recyclerViewCurrency.adapter = paymentMethoodDashboardAdapter

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view=inflater.inflate(R.layout.fragment_order_paymant, container, false)

        val llm = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        val llm2 = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)

        view.recyclerView.layoutManager = llm
        view. recyclerViewCurrency.layoutManager = llm2
        mPresenter = CurrencyTypePresenter(requireActivity())
        mPresenter!!.delegate = this
        paymentMethoodPresenter = PaymentMethoodPresnter(requireActivity())
        paymentMethoodPresenter!!.delegate = this

        mPresenter!!.getCurrencyType()
        paymentMethoodPresenter!!.getPaymentMethood()
        dashboardAdapter= CurrencyTypeAdapter( currencyModel,requireActivity())
//        dashboardAdapter.setOnClickItemCategory(this)
        paymentMethoodDashboardAdapter= PaymentAdapter(paymentMethoodModel,requireActivity())
//        paymentMethoodDashboardAdapter.setOnClickItemCategory(this)
        view.recyclerView.adapter = dashboardAdapter
        view.recyclerViewCurrency.adapter = paymentMethoodDashboardAdapter
        return view
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetCurrencyTypeSuccess(response: CurrencyTypeModel) {
        try {
            currencyModel.addAll(response.data)
            dashboardAdapter = CurrencyTypeAdapter( currencyModel,requireActivity())

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
        paymentMethoodDashboardAdapter = PaymentAdapter(paymentMethoodModel,requireActivity())

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