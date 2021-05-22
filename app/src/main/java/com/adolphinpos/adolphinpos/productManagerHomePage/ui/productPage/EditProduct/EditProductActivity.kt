package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct.barcode.ProductBarcodeFragment
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct.info.ProductInfoFragment
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct.qrCode.ProductQrCodeFragment
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.EditProduct.stock.ProductStockFragment
import kotlinx.android.synthetic.main.activity_edit_product.*


class EditProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_product)
        val window = this.window
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        setSupportActionBar(toolbar)

        supportActionBar!!.setDisplayHomeAsUpEnabled(true)


        setupViewPager(viewpager)

        close.setOnClickListener {
            finish()
        }
        tabs.setupWithViewPager(viewpager)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(ProductInfoFragment(), "info")
        adapter.addFragment(ProductBarcodeFragment(), "barcode")
        adapter.addFragment(ProductQrCodeFragment(), "QR code")
        adapter.addFragment(ProductStockFragment(), "Stock opt.")
        viewPager.adapter = adapter
    }

    internal class ViewPagerAdapter(manager: FragmentManager) :
        FragmentPagerAdapter(manager) {
        private val mFragmentList: MutableList<Fragment> = ArrayList()
        private val mFragmentTitleList: MutableList<String> = ArrayList()
        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFragment(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return mFragmentTitleList[position]
        }
    }
}