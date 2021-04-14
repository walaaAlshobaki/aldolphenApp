package com.adolphinpos.adolphinpos.productManagerHomePage.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.gallery.CashDrawerModel
import kotlinx.android.synthetic.main.fragment_slideshow.view.*


class SlideshowFragment : Fragment() , DashboardAdapter.OnItemselectedDelegate{

    var cashDrawerModel: ArrayList<CashDrawerModel> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var ItemsdashboardAdapter: DashboardAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        val linearVertical3 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        root.recyclerViewTransactions!!.layoutManager = linearVertical2
        root.recyclerViewTransactions!!.setHasFixedSize(true)
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"Transactions")
        dashboardAdapter.setOnClickItemCategory(this)
        root.recyclerViewItem!!.layoutManager = linearVertical3
        root.recyclerViewItem!!.setHasFixedSize(true)
        ItemsdashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"items")
        ItemsdashboardAdapter.setOnClickItemCategory(this)
        root.recyclerViewItem.adapter = ItemsdashboardAdapter
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
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"Transactions")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewTransactions.adapter = dashboardAdapter
        ItemsdashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"items")
        ItemsdashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewItem.adapter = ItemsdashboardAdapter
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }
}