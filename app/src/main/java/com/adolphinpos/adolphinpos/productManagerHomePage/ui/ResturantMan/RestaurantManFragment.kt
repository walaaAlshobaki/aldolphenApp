package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.fragment_restaurant_man.view.*

class RestaurantManFragment : Fragment() , DashboardAdapter.OnItemselectedDelegate{
    var hallsModel: ArrayList<HallsModel> = ArrayList()
    var hallsInfoModel: ArrayList<HallsInfoModel> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var infoDashboardAdapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_restaurant_man, container, false)
        // Inflate the layout for this fragment

        val llm = GridLayoutManager(activity, 9)
        llm.orientation = LinearLayoutManager.VERTICAL
        root.recyclerView.layoutManager = llm
        root.recyclerView!!.setHasFixedSize(true)
        dashboardAdapter = DashboardAdapter(requireActivity(), hallsModel,"hallsModel")
        dashboardAdapter.setOnClickItemCategory(this)
        root.recyclerView.adapter = dashboardAdapter
        val llm2 = GridLayoutManager(activity, 3)
        llm.orientation = LinearLayoutManager.VERTICAL
        root.recyclerViewList.layoutManager = llm2
        root.recyclerViewList!!.setHasFixedSize(true)
        infoDashboardAdapter = DashboardAdapter(requireActivity(), hallsInfoModel,"hallsInfoModel")
        infoDashboardAdapter.setOnClickItemCategory(this)
        root.recyclerViewList.adapter = infoDashboardAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hallsModel.add(HallsModel(1,1,R.drawable.ic_free))
        hallsModel.add(HallsModel(2,2,R.drawable.ic_reserved))
        hallsModel.add(HallsModel(3,3,R.drawable.ic_checkedin))
        hallsModel.add(HallsModel(4,4,R.drawable.ic_ordered))
        hallsModel.add(HallsModel(5,5,R.drawable.ic_blocked))
        hallsModel.add(HallsModel(6,6,R.drawable.ic_delivered))
        hallsModel.add(HallsModel(1,1,R.drawable.ic_free))
        hallsModel.add(HallsModel(2,2,R.drawable.ic_reserved))
        hallsModel.add(HallsModel(3,3,R.drawable.ic_checkedin))
        hallsModel.add(HallsModel(4,4,R.drawable.ic_ordered))
        hallsModel.add(HallsModel(5,5,R.drawable.ic_blocked))
        hallsModel.add(HallsModel(6,6,R.drawable.ic_delivered))
        hallsModel.add(HallsModel(1,1,R.drawable.ic_free))
        hallsModel.add(HallsModel(2,2,R.drawable.ic_reserved))
        hallsModel.add(HallsModel(3,3,R.drawable.ic_checkedin))
        hallsModel.add(HallsModel(4,4,R.drawable.ic_ordered))
        hallsModel.add(HallsModel(5,5,R.drawable.ic_blocked))
        hallsModel.add(HallsModel(6,6,R.drawable.ic_delivered))
        hallsModel.add(HallsModel(1,1,R.drawable.ic_free))
        hallsModel.add(HallsModel(2,2,R.drawable.ic_reserved))
        hallsModel.add(HallsModel(3,3,R.drawable.ic_checkedin))
        hallsModel.add(HallsModel(4,4,R.drawable.ic_ordered))
        hallsModel.add(HallsModel(5,5,R.drawable.ic_blocked))
        hallsModel.add(HallsModel(6,6,R.drawable.ic_delivered))
        dashboardAdapter = DashboardAdapter(requireActivity(), hallsModel,"hallsModel")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerView.adapter = dashboardAdapter

        hallsInfoModel.add(HallsInfoModel(1,1,"",R.drawable.ic_free_info))
        hallsInfoModel.add(HallsInfoModel(2,2,"",R.drawable.ic_reserved_info))
        hallsInfoModel.add(HallsInfoModel(3,3,"",R.drawable.ic_checkedinfo))
        hallsInfoModel.add(HallsInfoModel(4,4,"",R.drawable.ic_ordered_info))
        hallsInfoModel.add(HallsInfoModel(5,5,"",R.drawable.ic_blocked_info))
        hallsInfoModel.add(HallsInfoModel(6,6,"",R.drawable.ic_delivered_info))
        infoDashboardAdapter = DashboardAdapter(requireActivity(), hallsInfoModel,"hallsInfoModel")
        infoDashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewList.adapter = infoDashboardAdapter
    }
    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }


}