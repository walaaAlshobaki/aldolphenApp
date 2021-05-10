package com.adolphinpos.adolphinpos.registeration.country

import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.adolphinpos.adolphinpos.Adapters.RecyclerAdapter
import com.adolphinpos.adolphinpos.R
import com.adolphinpos.adolphinpos.Splash.common
import com.adolphinpos.adolphinpos.helper.MessageEvent
import com.adolphinpos.adolphinpos.helper.RxBus
import kotlinx.android.synthetic.main.activity_country.*


class CountryActivity : AppCompatActivity(),CountryDelegate, RecyclerAdapter.ItemClickListener {

    private lateinit var countryAdapter: RecyclerAdapter

    var mPresenter: CountryPresenter?=null
    var countryModel: ArrayList<CountryModel.Data> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country)
        if (android.os.Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
        }




        mPresenter=CountryPresenter(this)
        mPresenter!!.delegate = this
        val llm = GridLayoutManager(this, 4)
        recyclerView.layoutManager = llm


        countryAdapter= RecyclerAdapter(this, countryModel)
        countryAdapter.setOnClickItemCategory(this)
        recyclerView.adapter = countryAdapter
       mPresenter!!.getCountry()
    }





    override fun didGetCountrySuccess(response: CountryModel) {
        try {



            val iterator = (response.data.indices).iterator()

            if (iterator.hasNext()) {
                iterator.next()
            }

// do something with the rest of elements
            iterator.forEach {
               var call= ""
               var region= ""
                if (response.data[it].callingCodes.isNullOrEmpty()){
                    call="33"
                }else{
                    call=response.data[it].callingCodes
                }
                if (response.data[it].region.isNullOrEmpty()){
                    region="33"
                }else{
                    region=response.data[it].region
                }
                countryModel.add(
                    CountryModel.Data(
                        response.data[it].alpha2code,
                        response.data[it].alpha3code,
                        call,
                        response.data[it].flag,
                        response.data[it].id,
                        response.data[it].name,
                        region,
                        "item"
                    )
                )

                countryAdapter.notifyDataSetChanged()
            }
            runOnUiThread {
                countryAdapter.notifyDataSetChanged()
                countryAdapter = RecyclerAdapter(this, countryModel)
                countryAdapter.setOnClickItemCategory(this)
                recyclerView.adapter = countryAdapter
            }
        } catch (ex: Exception) {

            Log.d("apiExepction inside", ex.localizedMessage)


        }


    }
    private fun emptyCell() {
        hideLoaderCell()
        countryModel.clear()
        runOnUiThread {
            countryAdapter.notifyDataSetChanged()
//            animation_view.visibility = View.VISIBLE
//            progress_bar.visibility = View.GONE
//            recyclerView.visibility = View.GONE
        }

    }
    fun hideLoaderCell() {

//        swipe_container!!.setRefreshing(false);
        for (i in countryModel.indices) {

            if (countryModel[i].type == "loader" || countryModel[i].type == "empty") {
                countryModel.removeAt(i)
            }


        }


    }

    override fun didGetCountryFail(msg: String) {
//        hideLoaderCell()
//        runOnUiThread {
//            emptyCell()
//        }
    }

    override fun didEmpty() {
        hideLoaderCell()
        runOnUiThread {
            emptyCell()
        }
    }



    override fun onSelectCountry(position: Int) {
        RxBus.publish(MessageEvent(1, countryModel[position]))
        Log.d("WWWWWWWWWWW",countryModel[position].name)
      onBackPressed()

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}