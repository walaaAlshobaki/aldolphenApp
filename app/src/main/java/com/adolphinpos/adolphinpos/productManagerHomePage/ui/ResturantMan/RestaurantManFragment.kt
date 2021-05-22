package com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan

import android.content.Intent
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
import com.adolphinpos.adolphinpos.addCategory.AddCategoryActivity
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.product.ProductModel
import com.adolphinpos.adolphinpos.pupaupActivity.PopUpActivity
import kotlinx.android.synthetic.main.fragment_restaurant_man.*
import kotlinx.android.synthetic.main.fragment_restaurant_man.view.*

class RestaurantManFragment : Fragment() , DashboardAdapter.OnItemselectedDelegate,HallsDelegate{


    var mModelList: ArrayList<MainHallsModel.Data> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    var Free=0
    var Reserved=0
    var Checked=0
    var Ordered=0
    var Blocked=0
    var Delivered=0

    var mPresenter: HallsPresenter?=null
    var hallsModel: ArrayList<TableModel.Data> = ArrayList()
    var hallsInfoModel: ArrayList<HallsInfoModel> = ArrayList()

    var hallId=0
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

        val llm = GridLayoutManager(activity, 8)
        llm.orientation = LinearLayoutManager.VERTICAL
        root.recyclerView.setHasFixedSize(true)
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

        hallsInfoModel.add(HallsInfoModel(1,0,"reserved",R.drawable.ic_reserved_info))
        hallsInfoModel.add(HallsInfoModel(2,0,"Free",R.drawable.ic_free_info))
        hallsInfoModel.add(HallsInfoModel(3,0,"blocked",R.drawable.ic_blocked_info))
        hallsInfoModel.add(HallsInfoModel(4,0,"checked in",R.drawable.ic_checkedinfo))
        hallsInfoModel.add(HallsInfoModel(5,0,"Ordered",R.drawable.ic_ordered_info))
        hallsInfoModel.add(HallsInfoModel(6,0,"delivered",R.drawable.ic_delivered_info))
        infoDashboardAdapter = DashboardAdapter(requireActivity(), hallsInfoModel,"hallsInfoModel")
        infoDashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewList.adapter = infoDashboardAdapter
    }
    override fun onSelectItemCategory(position: Int) {

        for (n in mModelList.indices){
            mModelList[n].isSelected = n==position
        }


        hallId=mModelList[position].id!!
        mPresenter!!.getTables(mModelList[position].id!!)
        hallsModel.add(TableModel.Data(-2,"ADD Table",0,1))
        mAdapter = DashboardAdapter(requireContext(), mModelList,"HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        recyclerViewHalls.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun onSelectItemProduct(position: Int, action: String) {

        if (action=="add"){


            val i = Intent(requireActivity(), PopUpActivity::class.java)
            i.putExtra("action","halls")
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }else if(action=="table"){

            val i = Intent(requireActivity(), PopUpActivity::class.java)
            i.putExtra("action","table")
            i.putExtra("hallId",hallId)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)
        }
    }

    override fun didGetHallsySuccess(response: MainHallsModel) {
        mModelList.clear()
        mModelList.add(MainHallsModel.Data(-2," + ",true))
        mModelList.addAll(response.data)
        Log.d("didGetPoliicySuccess",mModelList.toString())


        requireActivity().runOnUiThread {
            mAdapter!!.notifyDataSetChanged()

        }
    }

    override fun didGetHallsFail(msg: String) {

    }

    override fun didEmpty() {
    }

    override fun didGetTablesSuccess(response: TableModel) {
        hallsModel.clear()
        hallsInfoModel.clear()
        hallsModel.add(TableModel.Data(-2,"ADD Table",0,1))

         Free=0
         Reserved=0
         Checked=0
         Ordered=0
         Blocked=0
         Delivered=0
        hallsModel.addAll(response.data)
        Log.d("didGetPoliicySuccess",hallsModel.toString())
        requireActivity().runOnUiThread {
            dashboardAdapter!!.notifyDataSetChanged()

        }


        val iterator = (response.data.indices).iterator()

        if (iterator.hasNext()) {
            iterator.next()
        }

// do something with the rest of elements
        iterator.forEach {

            if (response.data[it].status == 1) {
                Free++

            }else if (response.data[it].status == 2) {
                Reserved++

            }

            else if (response.data[it].status == 3) {
                Checked++

            }
            else if (response.data[it].status == 4) {
                Ordered++

            }   else if (response.data[it].status == 5) {
                Blocked++

            }

            else if (response.data[it].status == 6) {
                Delivered++

            }





        }




        hallsInfoModel.add(HallsInfoModel(1,Reserved,"reserved",R.drawable.ic_reserved_info))
        hallsInfoModel.add(HallsInfoModel(2,Free,"Free",R.drawable.ic_free_info))
        hallsInfoModel.add(HallsInfoModel(3,Blocked,"blocked",R.drawable.ic_blocked_info))
        hallsInfoModel.add(HallsInfoModel(4,Checked,"checked in",R.drawable.ic_checkedinfo))
        hallsInfoModel.add(HallsInfoModel(5,Ordered,"Ordered",R.drawable.ic_ordered_info))
        hallsInfoModel.add(HallsInfoModel(6,Delivered,"delivered",R.drawable.ic_delivered_info))

        infoDashboardAdapter.notifyDataSetChanged()

    }

    override fun didGetTablesFail(msg: String) {

        hallsInfoModel.clear()
        hallsInfoModel.add(HallsInfoModel(1,Reserved,"reserved",R.drawable.ic_reserved_info))
        hallsInfoModel.add(HallsInfoModel(2,Free,"Free",R.drawable.ic_free_info))
        hallsInfoModel.add(HallsInfoModel(3,Blocked,"blocked",R.drawable.ic_blocked_info))
        hallsInfoModel.add(HallsInfoModel(4,Checked,"checked in",R.drawable.ic_checkedinfo))
        hallsInfoModel.add(HallsInfoModel(5,Ordered,"Ordered",R.drawable.ic_ordered_info))
        hallsInfoModel.add(HallsInfoModel(6,Delivered,"delivered",R.drawable.ic_delivered_info))

        infoDashboardAdapter.notifyDataSetChanged()

    }

    override fun didEmptyTables() {

    }

    override fun onResume() {
        super.onResume()
        mPresenter!!.getHalls()
    }


}