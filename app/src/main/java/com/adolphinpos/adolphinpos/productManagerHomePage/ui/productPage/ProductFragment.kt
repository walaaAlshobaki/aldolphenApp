    package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.addCategory.AddCategoryActivity
import com.adolphinpos.adolphinpos.addCategory.CategoryDelegate
import com.adolphinpos.adolphinpos.addCategory.CategoryPresenter
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.product.ProductModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct.EditProductActivity
import kotlinx.android.synthetic.main.fragment_product.view.*


class ProductFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate , CategoryDelegate {
//    var categoryModel: ArrayList<CategoryModel> = ArrayList()
    var categoryModel: ArrayList<CategoryModelNew.Data> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    var mPresenter: CategoryPresenter? = null
    var productModel: ArrayList<ProductModel> = ArrayList()
    lateinit var category:RecyclerView
    lateinit var ProductsRec:RecyclerView
    private lateinit var mAdapterProductModel: DashboardAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        category=view.category
        ProductsRec=view.ProductsRec
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"categoryModel")

        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        category!!.layoutManager = linearVertical
        category.setHasFixedSize(true)
        category.setAdapter(mAdapter)

        productModel.add(ProductModel(-2,"ADD PRODUCT","",true))
        productModel.add(ProductModel(1,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(2,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(3,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(4,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(5,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        productModel.add(ProductModel(6,"Chicken Barbeque with molten cheese","",false))
        mAdapterProductModel = DashboardAdapter(requireActivity(), productModel,"productModel")
        mAdapterProductModel.notifyDataSetChanged()

        mAdapterProductModel.setOnClickItemCategory(this)
        val llm = GridLayoutManager(requireActivity(), 5)

        ProductsRec!!.layoutManager = llm
        ProductsRec.setHasFixedSize(true)
        ProductsRec.adapter = mAdapterProductModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_product, container, false)
        category=view.category
        ProductsRec=view.ProductsRec
        mPresenter = CategoryPresenter(requireActivity())
        mPresenter!!.delegate = this
        mPresenter!!.getCategories()
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"categoryModel")
        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        category!!.layoutManager = linearVertical
        category.setHasFixedSize(true)
        category.setAdapter(mAdapter)

        mAdapterProductModel = DashboardAdapter(requireActivity(), productModel,"productModel")
        mAdapterProductModel.notifyDataSetChanged()

        mAdapterProductModel.setOnClickItemCategory(this)
        val llm = GridLayoutManager(requireActivity(), 5)

       ProductsRec!!.layoutManager = llm
        ProductsRec.setHasFixedSize(true)
        ProductsRec.adapter = mAdapterProductModel
        return view
    }

    override fun onSelectItemCategory(position: Int) {
        if (categoryModel[position].id==-2){
            val i = Intent(requireActivity(), AddCategoryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        for (n in categoryModel.indices){
            categoryModel[n].isSelected = n==position
        }
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"categoryModel")
        mAdapter.setOnClickItemCategory(this)
        category.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }

    override fun onSelectItemProduct(position: Int, action: String) {
        if (productModel[position].id==-2){
            findNavController().navigate(R.id.AddProductItemFragment)

        }
        if (action.equals("edit")){
            val intent = Intent(
                activity,
                EditProductActivity::class.java
            )

            startActivity(intent)

        }

    }
    override fun didGetCategorySuccess(response: CategoryModelNew) {

        Log.d("$$$$$$$$$$$$$",response.toString())

        categoryModel.clear()
        categoryModel.add(CategoryModelNew.Data(-2,"","ADD CATEGORY",true))

        categoryModel.addAll(response.data)
        mAdapter = DashboardAdapter(requireActivity(), categoryModel,"categoryModel")

        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(requireActivity(), RecyclerView.HORIZONTAL, false)
        category!!.layoutManager = linearVertical
        category.setHasFixedSize(true)
        category.setAdapter(mAdapter)

        mAdapter!!.notifyDataSetChanged()
    }

    override fun didGetCategoryFail(msg: String) {

    }

    override fun didEmpty() {

    }


}