package com.adolphinpos.adolphinpos.productManagement

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.userInfo
import com.adolphinpos.adolphinpos.addCategory.AddCategoryActivity
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.employee_permissions.PoliicyPermissionModel
import com.adolphinpos.adolphinpos.helper.CircleTransform
import com.adolphinpos.adolphinpos.policyManagement.PolicyManagementActivity
import com.adolphinpos.adolphinpos.product.ProductModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_emp_permissions.*
import kotlinx.android.synthetic.main.activity_emp_permissions.recyclerView
import kotlinx.android.synthetic.main.activity_pos_setting.*
import kotlinx.android.synthetic.main.activity_pos_setting.userImage
import kotlinx.android.synthetic.main.activity_pos_setting.userName
import kotlinx.android.synthetic.main.activity_product_management.*

class ProductManagementActivity : AppCompatActivity(), DashboardAdapter.OnItemselectedDelegate {
    var categoryModel: ArrayList<CategoryModel> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    var productModel: ArrayList<ProductModel> = ArrayList()
    private lateinit var mAdapterProductModel: DashboardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_management)
        Picasso.get().load(R.drawable.user).transform(CircleTransform()).into(userImage)
        userName.text = userInfo.firstName + " " + userInfo.lastName

        categoryModel.add(CategoryModel(-2,"ADD CATEGORY","",true))
        categoryModel.add(CategoryModel(1,"sandwitches","",false))
        categoryModel.add(CategoryModel(2,"Dishes","",false))
        categoryModel.add(CategoryModel(3,"hot drinks","",false))
        categoryModel.add(CategoryModel(4,"cold drinks","",false))
        categoryModel.add(CategoryModel(5,"cold drinks","",false))
        categoryModel.add(CategoryModel(6,"sandwitches","",false))
        mAdapter = DashboardAdapter(this, categoryModel,"categoryModel")
        mAdapter.notifyDataSetChanged()

        mAdapter!!.setOnClickItemCategory(this)
        val linearVertical = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false)
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
        mAdapterProductModel = DashboardAdapter(this, productModel,"productModel")
        mAdapterProductModel.notifyDataSetChanged()

        mAdapterProductModel.setOnClickItemCategory(this)
        val llm = GridLayoutManager(this, 6)

        ProductsRec!!.layoutManager = llm
        ProductsRec.setHasFixedSize(true)
        ProductsRec.adapter = mAdapter

    }

    override fun onSelectItemCategory(position: Int) {
        if (categoryModel[position].id==-2){
            val i = Intent(this, AddCategoryActivity::class.java)
            i.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(i)

        }
        for (n in categoryModel.indices){
            categoryModel[n].isSelected = n==position
        }
        mAdapter = DashboardAdapter(this, categoryModel,"categoryModel")
        mAdapter.setOnClickItemCategory(this)
        category.adapter = mAdapter
        mAdapter.notifyDataSetChanged()
    }
}