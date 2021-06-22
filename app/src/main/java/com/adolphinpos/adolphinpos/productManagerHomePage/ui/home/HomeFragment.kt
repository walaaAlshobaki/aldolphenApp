package com.adolphinpos.adolphinpos.productManagerHomePage.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addCategory.CategoryDelegate
import com.adolphinpos.adolphinpos.addCategory.CategoryPresenter
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.home.ViewPruduct.ViewProductActivity
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.CategoryModelNew
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.ProductDelegate
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.ProductModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.ProductPresnter

import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate , CategoryDelegate, ProductDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var orderDashboardAdapter: DashboardAdapter
    //   var dashboardModel: ArrayList<HomeModel> = ArrayList()
    var categoryModel: ArrayList<CategoryModelNew.Data> = ArrayList()
    var mProductPresenter: ProductPresnter? = null
    private lateinit var mAdapter: DashboardAdapter
    var dashboardModel: ArrayList<ProductModel.Data.Data> = ArrayList()
    private lateinit var homeViewModel: HomeViewModel
    var mPresenter: CategoryPresenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        mPresenter = CategoryPresenter(requireActivity())
        mPresenter!!.delegate = this
        mPresenter!!.getCategories()
        mProductPresenter = ProductPresnter(requireActivity())
        mProductPresenter!!.delegate = this

        val llm = GridLayoutManager(activity, 4)
        llm.orientation = LinearLayoutManager.VERTICAL
       root.recyclerViewProduct.layoutManager = llm

        dashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"productManagmentModel")
        dashboardAdapter.setOnClickItemCategory(this)
        root.recyclerViewProduct.adapter = dashboardAdapter


        val linearVertical2 = LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        root.orderRecyclerView!!.layoutManager = linearVertical2
        root.orderRecyclerView!!.setHasFixedSize(true)
        orderDashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"orderDashboardAdapter")
        orderDashboardAdapter.setOnClickItemCategory(this)
        root.orderRecyclerView.adapter = orderDashboardAdapter
//        })

        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"mainCategoryModel")
        mAdapter.notifyDataSetChanged()

        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        root.recyclerViewCategory!!.layoutManager = linearVertical
        root.recyclerViewCategory!!.setHasFixedSize(true)
        root.recyclerViewCategory!!.adapter = mAdapter
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setDashbordData()
        dashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"productManagmentModel")
        dashboardAdapter.setOnClickItemCategory(this)
        view.recyclerViewProduct.adapter = dashboardAdapter

        orderDashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"orderDashboardAdapter")
        orderDashboardAdapter.setOnClickItemCategory(this)
        view.orderRecyclerView.adapter = orderDashboardAdapter

        view.Proceed.setOnClickListener {

            ProceedActivity.visibility=View.VISIBLE



        }
        close.setOnClickListener {
            ProceedActivity.visibility=View.GONE

        }

        onlineCon.setOnClickListener {
            ProceedActivity.visibility=View.GONE

            findNavController().navigate(R.id.procees)
        }


        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"mainCategoryModel")
        mAdapter.notifyDataSetChanged()
        mAdapter.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        view.recyclerViewCategory!!.layoutManager = linearVertical
        view.recyclerViewCategory!!.setHasFixedSize(true)
        view.recyclerViewCategory!!.adapter = mAdapter





    }
    fun setDashbordData() {


        mProductPresenter!!.getProduct(userInfo.companyId.toInt(),1,10)
//
//
//
//        dashboardAdapter = DashboardAdapter(this, dashboardModel)



//        dashboardModel.add(data1)
//        dashboardModel.add(data2)
        Log.d("didGetServicesSuccess",dashboardModel.toString())
//        dashboardAdapter = DashboardAdapter(requireActivity(), dashboardModel,"productManagmentModel")

//        dashboardAdapter.notifyDataSetChanged()

    }
    override fun onSelectItemCategory(position: Int) {
        dashboardModel.clear()
        mAdapter!!.notifyDataSetChanged()
        mProductPresenter!!.getProduct(userInfo.companyId.toInt(),1,10,categoryModel[position].id!!)
    }

    override fun onSelectItemProduct(position: Int, action: String) {
        if (action=="productManagmentModel"){
                        val intent = Intent(
                            requireActivity(),
                            ViewProductActivity::class.java
                        )

                        requireActivity().startActivity(intent)
        }
    }

    override fun didGetCategorySuccess(response: CategoryModelNew) {
        Log.d("$$$$$$$$$$$$$",response.toString())

        categoryModel.clear()
        mAdapter!!.notifyDataSetChanged()

        categoryModel.addAll(response.data)
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"mainCategoryModel")

        mAdapter!!.setOnClickItemCategory(this)

        mAdapter!!.notifyDataSetChanged()

    }

    override fun didGetCategoryFail(msg: String) {

    }

    override fun didEmpty() {

    }

    override fun didGetProductSuccess(response: ProductModel.Data) {


        if (response.data.isEmpty()){
            Log.d("GGGGGGGGGGGGGGGGGGGGGGG",response.data.toString())
            dashboardModel.clear()
            dashboardAdapter.notifyDataSetChanged()

        }else{
            dashboardModel.clear()
            Log.d("GGGGGGGGGGGGGGGGGGGGGGG",response.data.toString())

            dashboardModel.addAll(response.data)
            dashboardAdapter.notifyDataSetChanged()
        }



    }

    override fun didGetProductFail(msg: String) {

    }

    override fun didProductEmpty() {

    }
}