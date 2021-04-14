package com.adolphinpos.adolphinpos.productManagerHomePage.ui.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import kotlinx.android.synthetic.main.fragment_gallery.view.*

class GalleryFragment : Fragment() , DashboardAdapter.OnItemselectedDelegate{

    var cashDrawerModel: ArrayList<CashDrawerModel> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_gallery, container, false)
        val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        root.recyclerViewCashDrawer!!.layoutManager = linearVertical2
        root.recyclerViewCashDrawer!!.setHasFixedSize(true)
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"CashDrawer")
        dashboardAdapter.setOnClickItemCategory(this)
        root.recyclerViewCashDrawer.adapter = dashboardAdapter
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
        dashboardAdapter = DashboardAdapter(requireActivity(), cashDrawerModel,"CashDrawer")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewCashDrawer.adapter = dashboardAdapter
    }
    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }
}