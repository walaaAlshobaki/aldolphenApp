package com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.DashboardAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.ResturantMan.MainHallsModel
import com.adolphinpos.adolphinpos.productManagerHomePage.ui.productPage.scanActivity.ScanActivity
import com.adolphinpos.adolphinpos.registeration.country.CountryModel
import kotlinx.android.synthetic.main.product_info_item.*
import kotlinx.android.synthetic.main.product_info_item.view.*


class AddProductItemFragment : Fragment(), DashboardAdapter.OnItemselectedDelegate {
    private lateinit var productNames: RecyclerView
    var mModelList: ArrayList<MainHallsModel.Data> = ArrayList()
    private lateinit var mAdapter: DashboardAdapter
    val DAYS_OPTIONS = arrayOf<CharSequence>(
        "Sunday",
        "Monday",
        "Tuesday",
        "Wednesday",
        "Thursday",
        "Friday",
        "Saturday"
    )
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))
        mModelList.add(MainHallsModel.Data(1, "Fries"))

        mAdapter = DashboardAdapter(requireActivity(), mModelList, "HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        view.productNames.adapter = mAdapter


        view.lang.setOnClickListener {
            val intent = Intent(
                requireActivity(),
                ProductNameActivity::class.java
            )

            requireActivity().startActivity(intent)
        }


        view. sign.setOnClickListener {
            val intent = Intent(
                requireActivity(),
                MainNewItemActivity::class.java
            )

            requireActivity().startActivity(intent)

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view=inflater.inflate(R.layout.fragment_add_product_item, container, false)
        productNames=view.productNames
        mAdapter =DashboardAdapter(requireActivity(), mModelList, "HallsViewHolder")
        mAdapter.setOnClickItemCategory(this)
        val llm = GridLayoutManager(activity, 3)
        llm.orientation = LinearLayoutManager.VERTICAL
        productNames.layoutManager = llm
        productNames.setHasFixedSize(true)
        productNames.adapter = mAdapter

        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 50) {
                barcode.text = it.message.toString()
            }
        }

       view.barcodeBtn.setOnClickListener {
           val intent = Intent(activity, ScanActivity::class.java)
           startActivity(intent)
       }
        return view
    }

    override fun onResume() {
        super.onResume()
        RxBus.listen(MessageEvent::class.java).subscribe {
            if (it.action == 50) {
                barcode.text = it.message.toString()
            }
        }

    }

    override fun onSelectItemCategory(position: Int) {

    }

    override fun onSelectItemProduct(position: Int, action: String) {

    }


}