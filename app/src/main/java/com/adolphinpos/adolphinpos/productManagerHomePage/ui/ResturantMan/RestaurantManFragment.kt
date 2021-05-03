package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import kotlinx.android.synthetic.main.fragment_restaurant_man.*
import kotlinx.android.synthetic.main.fragment_restaurant_man.view.*

class RestaurantManFragment : Fragment() , DashboardAdapter.OnItemselectedDelegate,HallsDelegate{


    var mModelList: ArrayList<MainHallsModel.Data> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter

    var mPresenter: HallsPresenter?=null
    var hallsModel: ArrayList<TableModel.Data> = ArrayList()
    var hallsInfoModel: ArrayList<HallsInfoModel> = ArrayList()
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var infoDashboardAdapter: DashboardAdapter
    private lateinit var recyclerViewHalls: RecyclerView

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
        mPresenter=HallsPresenter(requireActivity())
        mPresenter!!.delegate = this

        mAdapter =DashboardAdapter(requireActivity(), mModelList,"HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)

        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        recyclerViewHalls=root.recyclerViewHalls
      recyclerViewHalls!!.layoutManager = linearVertical

        recyclerViewHalls.setHasFixedSize(true)
        recyclerViewHalls.adapter = mAdapter
        getListData()

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

    fun getListData(){
        mPresenter!!.getHalls()


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dashboardAdapter = DashboardAdapter(requireActivity(), hallsModel,"hallsModel")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerView.adapter = dashboardAdapter

        hallsInfoModel.add(HallsInfoModel(1,3,"Free",R.drawable.ic_free_info))
        hallsInfoModel.add(HallsInfoModel(2,0,"reserved",R.drawable.ic_reserved_info))
        hallsInfoModel.add(HallsInfoModel(3,0,"checked in",R.drawable.ic_checkedinfo))
        hallsInfoModel.add(HallsInfoModel(4,0,"Ordered",R.drawable.ic_ordered_info))
        hallsInfoModel.add(HallsInfoModel(5,0,"blocked",R.drawable.ic_blocked_info))
        hallsInfoModel.add(HallsInfoModel(6,0,"delivered",R.drawable.ic_delivered_info))
        infoDashboardAdapter = DashboardAdapter(requireActivity(), hallsInfoModel,"hallsInfoModel")
        infoDashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewList.adapter = infoDashboardAdapter
    }
    override fun onSelectItemCategory(position: Int) {

        for (n in mModelList.indices){
            mModelList[n].isSelected = n==position
        }


        mPresenter!!.getTables(mModelList[position].id!!)
        mAdapter = DashboardAdapter(requireContext(), mModelList,"HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        recyclerViewHalls.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }

    override fun didGetHallsySuccess(response: MainHallsModel) {
        mModelList.addAll(response.data)
        Log.d("didGetPoliicySuccess",mModelList.toString())


        activity!!.runOnUiThread {
            mAdapter!!.notifyDataSetChanged()

        }
    }

    override fun didGetHallsFail(msg: String) {

    }

    override fun didEmpty() {
    }

    override fun didGetTablesSuccess(response: TableModel) {
        hallsModel.clear()
        hallsModel.addAll(response.data)
        Log.d("didGetPoliicySuccess",hallsModel.toString())


        activity!!.runOnUiThread {
            dashboardAdapter!!.notifyDataSetChanged()

        }

    }

    override fun didGetTablesFail(msg: String) {

    }

    override fun didEmptyTables() {

    }


}