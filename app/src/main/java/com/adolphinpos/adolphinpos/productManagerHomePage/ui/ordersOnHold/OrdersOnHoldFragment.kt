package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ordersOnHold

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.gallery.CashDrawerModel
import kotlinx.android.synthetic.main.fragment_orders_on_hold.view.*



class OrdersOnHoldFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate {
    var cashDrawerModel: ArrayList<CashDrawerModel> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_orders_on_hold, container, false)
        val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        root.recyclerViewOrder!!.layoutManager = linearVertical2
        root.recyclerViewOrder!!.setHasFixedSize(true)
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"orderOn")
        dashboardAdapter.setOnClickItemCategory(this)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cashDrawerModel.add(CashDrawerModel(1))
        cashDrawerModel.add(CashDrawerModel(2))
        cashDrawerModel.add(CashDrawerModel(3))
        cashDrawerModel.add(CashDrawerModel(4))
        cashDrawerModel.add(CashDrawerModel(5))
        cashDrawerModel.add(CashDrawerModel(6))
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"orderOn")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewOrder.adapter = dashboardAdapter
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }


}