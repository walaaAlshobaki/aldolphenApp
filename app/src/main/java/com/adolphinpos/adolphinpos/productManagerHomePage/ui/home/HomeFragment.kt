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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.home.ViewPruduct.ViewProductActivity
import kotlinx.android.synthetic.main.fragment_home.view.*

class HomeFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate {
    private lateinit var dashboardAdapter: DashboardAdapter
    private lateinit var orderDashboardAdapter: DashboardAdapter
    //   var dashboardModel: ArrayList<HomeModel> = ArrayList()
    var categoryModel: ArrayList<CategoryModel> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    var dashboardModel: ArrayList<productManagmentModel> = ArrayList()
    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
//        homeViewModel.text.observe(viewLifecycleOwner, Observer {
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



            findNavController().navigate(R.id.procees)

        }

        categoryModel.add(CategoryModel(1,"sandwitches","",false))
        categoryModel.add(CategoryModel(2,"Dishes","",false))
        categoryModel.add(CategoryModel(3,"hot drinks","",false))
        categoryModel.add(CategoryModel(4,"cold drinks","",false))
        categoryModel.add(CategoryModel(5,"cold drinks","",false))
        categoryModel.add(CategoryModel(6,"sandwitches","",false))
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"mainCategoryModel")
        mAdapter.notifyDataSetChanged()
        mAdapter.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        view.recyclerViewCategory!!.layoutManager = linearVertical
        view.recyclerViewCategory!!.setHasFixedSize(true)
        view.recyclerViewCategory!!.adapter = mAdapter





    }
    fun setDashbordData() {

        val data1 = productManagmentModel(1,"test1",3.5,10,"Thu Nov 28 2019","20","300 GM","https://i.pinimg.com/564x/c2/01/a3/c201a3fe06377dd8421a128ddd58f693.jpg","drinks")
        val data2 = productManagmentModel(2,"test2",3.5,10,"Thu Nov 28 2019","20","300 GM","https://i.pinimg.com/564x/c2/01/a3/c201a3fe06377dd8421a128ddd58f693.jpg","sandwitches")
        val data3 = productManagmentModel(3,"test3",3.5,10,"Thu Nov 28 2019","20","300 GM","https://i.pinimg.com/564x/c2/01/a3/c201a3fe06377dd8421a128ddd58f693.jpg","Meals")
        val data4 = productManagmentModel(4,"test4",3.5,10,"Thu Nov 28 2019","20","300 GM","https://i.pinimg.com/564x/c2/01/a3/c201a3fe06377dd8421a128ddd58f693.jpg","Meals")

//        val data3 = HomeModel(" ")
//
//
//
        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)
        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)
        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)
        dashboardModel.add(data1)
        dashboardModel.add(data2)
        dashboardModel.add(data3)
        dashboardModel.add(data4)

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
        TODO("Not yet implemented")
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
}