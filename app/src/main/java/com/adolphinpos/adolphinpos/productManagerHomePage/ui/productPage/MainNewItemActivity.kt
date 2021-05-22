package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.categoryes.CategoryModel
import com.adolphinpos.adolphinpos.product.ProductModel
import kotlinx.android.synthetic.main.activity_main_new_item.*

class MainNewItemActivity : AppCompatActivity() , DashboardAdapter.OnItemselectedDelegate{
    var categoryModel: ArrayList<CategoryModel> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    var productModel: ArrayList<ProductModel> = ArrayList()
    private lateinit var mAdapterProductModel: DashboardAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_new_item)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        close.setOnClickListener {
            finish()
        }
        categoryModel.add(CategoryModel(1,"sandwitches","",false))
        categoryModel.add(CategoryModel(2,"Dishes","",false))
        categoryModel.add(CategoryModel(3,"hot drinks","",false))
        categoryModel.add(CategoryModel(4,"cold drinks","",false))
        categoryModel.add(CategoryModel(5,"cold drinks","",false))
        categoryModel.add(CategoryModel(6,"sandwitches","",false))
        categoryModel.add(CategoryModel(6,"sandwitches","",false))
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
        val llm = GridLayoutManager(this, 5)

        ProductsRec!!.layoutManager = llm
        ProductsRec.setHasFixedSize(true)
        ProductsRec.adapter = mAdapterProductModel
    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {
        val intent = Intent(
            this,
            ProductNewItemActivity::class.java
        )

       startActivity(intent)
        finish()
    }
}